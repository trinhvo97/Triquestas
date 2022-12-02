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
import sg.triquesta.model.dto.response.applicant.ApplicantCurrentLoan;
import sg.triquesta.model.dto.response.applicant.ApplicantResponse;
import sg.triquesta.model.dto.response.applicant.ApplicantResponses;
import sg.triquesta.model.dto.response.credit.CreditFacilityResponse;
import sg.triquesta.model.dto.response.loan.LoanResponse;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.entity.credit.CreditFacility;
import sg.triquesta.model.entity.loan.Loan;
import sg.triquesta.model.filter.BaseFilter;
import sg.triquesta.repository.ApplicantRepository;
import sg.triquesta.service.credit.CreditFacilityService;
import sg.triquesta.service.loan.LoanService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApplicantServiceImpl implements ApplicantService{

    private final ApplicantRepository applicantRepository;
    private final CreditFacilityService creditFacilityService;
    private final ApplicantService applicantService;
    private final LoanService loanService;

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
                .applicants(ApplicantResponse.fromApplicants(applicants.getContent()))
                .build();

    }

    @Override
    public Applicant getApplicantById(String applicantId) {
        return applicantRepository.findById(applicantId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find Applicant with applicantId {%s}",applicantId)));
    }

    @Override
    public ApplicantResponse getApplicantByLoan(String applicantId) {
        Applicant applicant = applicantService.getApplicantById(applicantId);

        List<CreditFacility> creditFacilities = creditFacilityService.findByApplicantId(applicantId);
        List<CreditFacilityResponse> creditFacilitiesDto = new ArrayList<>();

        creditFacilities.forEach(credit -> {
            CreditFacilityResponse creditFacilityDto = CreditFacilityResponse.builder()
                    .id(credit.getId())
                    .currency(credit.getCurrency())
                    .startDate(credit.getStartDate())
                    .endDate(credit.getEndDate())
                    .loans(LoanResponse.fromLoans(credit.getLoans()))
                    .build();
            creditFacilitiesDto.add(creditFacilityDto);
        });

        return ApplicantResponse.fromApplicant(applicant, creditFacilitiesDto);
    }

    @Override
    public ApplicantCurrentLoan getTotalCurrentLoan(String applicationId) {

        List<CreditFacility> creditFacilities= creditFacilityService.findByApplicantId(applicationId);

        if(CollectionUtils.isEmpty(creditFacilities)){
            return null;
        }

        List<String> creditFacilityIds = creditFacilities.stream().map(s -> s.getId()).collect(Collectors.toList());
        List<Loan> loans = loanService.getLoanByCreditFacilityIds(creditFacilityIds);

        BigDecimal totalAmount  = loans
                .stream().map(s-> s.getAmount())
                .reduce(BigDecimal.valueOf(0), (x, y)-> x.add(y));

        BigDecimal totalRemainAmount = loans
                .stream().map(s-> s.getRemainAmount())
                .reduce(BigDecimal.valueOf(0), (x, y)-> x.add(y));

        BigDecimal totalPaymentAmount = totalAmount.subtract(totalRemainAmount);

        return ApplicantCurrentLoan.builder()
                .totalAmount(totalAmount)
                .totalPaymentAmount(totalPaymentAmount)
                .totalRemainAmount(totalRemainAmount)
                .build();
    }
}
