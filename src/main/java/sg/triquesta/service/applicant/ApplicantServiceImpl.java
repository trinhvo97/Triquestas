package sg.triquesta.service.applicant;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sg.triquesta.model.dto.request.enums.SortByApplication;
import sg.triquesta.model.dto.response.applicant.ApplicantCurrentLoanDto;
import sg.triquesta.model.dto.response.applicant.ApplicantResponseDto;
import sg.triquesta.model.dto.response.applicant.ApplicantResponses;
import sg.triquesta.model.dto.response.credit.CreditFacilityDto;
import sg.triquesta.model.dto.response.loan.LoanDto;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.entity.credit.CreditFacility;
import sg.triquesta.model.filter.BaseFilter;
import sg.triquesta.repository.ApplicantRepository;
import sg.triquesta.service.credit.CreditFacilityService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApplicantServiceImpl implements ApplicantService{

    private final ApplicantRepository applicantRepository;
    private final CreditFacilityService creditFacilityService;
    private final ApplicantService applicantService;

    @Override
    public void saveApplicant(sg.triquesta.model.dto.request.applicant.ApplicantDto applicantDto) {
        Applicant applicant = Applicant.builder()
                .nameApplicant(applicantDto.getName())
                .email(applicantDto.getEmail())
                .address(applicantDto.getAddress())
                .mobile(applicantDto.getMobile())
                .build();

        applicantRepository.save(applicant);
    }

    @Override
    public ApplicantResponses getApplicants(BaseFilter baseFilter) {
        Sort sort = Sort.by(baseFilter.getOrderBy(), SortByApplication.CREATION_TIME.getField());
        Pageable pageable = PageRequest.of(baseFilter.getPage(), baseFilter.getSize(), sort);
        String searchEmailOrName = "%" + baseFilter.getSearch() + "%";

        Page<Applicant> applicants = applicantRepository.findByNameApplicantLikeOrEmailLike(searchEmailOrName,searchEmailOrName,pageable);

        return ApplicantResponses.builder()
                .page(baseFilter.getPage())
                .size(applicants.getSize())
                .total(applicants.getTotalElements())
                .applicants(ApplicantResponseDto.fromApplicants(applicants.getContent()))
                .build();

    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find Applicant with applicantId {%s}",applicantId)));
    }

    @Override
    public ApplicantResponseDto getApplicantByLoan(String applicantId) {

        List<CreditFacility> creditFacilities = creditFacilityService.findByApplicantId(applicantId);
        List<CreditFacilityDto> creditFacilitiesDto = new ArrayList<>();

        creditFacilities.forEach(credit -> {
            CreditFacilityDto creditFacilityDto = CreditFacilityDto.builder()
                    .id(credit.getId())
                    .currency(credit.getCurrency())
                    .startDate(credit.getStartDate())
                    .endDate(credit.getEndDate())
                    .loans(LoanDto.fromLoans(credit.getLoans()))
                    .build();
            creditFacilitiesDto.add(creditFacilityDto);
        });

        Applicant applicant = applicantService.getApplicantById(applicantId);

        return ApplicantResponseDto.builder()
                .id((applicant.getId()))
                .name(applicant.getNameApplicant())
                .email(applicant.getEmail())
                .address(applicant.getAddress())
                .mobile(applicant.getMobile())
                .creditFacilities(creditFacilitiesDto)
                .build();
    }

    @Override
    public ApplicantCurrentLoanDto getTotalCurrentLoan(String applicationId) {

        CreditFacility creditFacility = creditFacilityService.getCreditFacilityById(applicationId);
        if(CollectionUtils.isEmpty(creditFacility.getLoans())){
            return null;
        }

        BigDecimal totalAmount  = creditFacility.getLoans()
                .stream().map(s-> s.getAmount())
                .reduce(BigDecimal.valueOf(0), (x, y)-> x.add(y));

        BigDecimal totalRemainAmount = creditFacility.getLoans()
                .stream().map(s-> s.getRemainAmount())
                .reduce(BigDecimal.valueOf(0), (x, y)-> x.add(y));

        BigDecimal totalPaymentAmount = totalAmount.subtract(totalRemainAmount);

        return ApplicantCurrentLoanDto.builder()
                .totalAmount(totalAmount)
                .totalPaymentAmount(totalPaymentAmount)
                .totalRemainAmount(totalRemainAmount)
                .build();
    }
}
