package sg.triquesta.service.credit;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sg.triquesta.model.dto.request.credit.CreditFacilityDto;
import sg.triquesta.model.dto.request.loan.LoanTypeLimitDto;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.entity.credit.CreditFacility;
import sg.triquesta.model.entity.loan.LoanLimit;
import sg.triquesta.model.entity.loan.LoanType;
import sg.triquesta.repository.*;
import sg.triquesta.service.applicant.ApplicantService;
import sg.triquesta.service.loan.LoanLimitService;
import sg.triquesta.service.loan.LoanTypeService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CreditFacilityServiceImpl implements CreditFacilityService{
    private final CreditFacilityRepository creditFacilityRepository;
    private final LoanTypeService loanTypeService;
    private final LoanLimitService loanLimitService;
    @Lazy
    private final ApplicantService applicantService;

    @Override
    @Transactional
    public void createCreditFacility(String applicantId, CreditFacilityDto creditFacilityDto) {
        Applicant applicant =applicantService.getApplicantById(applicantId);

        CreditFacility creditFacilityCreation = CreditFacility.builder()
                .totalLimit(creditFacilityDto.getTotalLimit())
                .applicant(applicant)
                .currency(creditFacilityDto.getCurrency())
                .startDate(creditFacilityDto.getStartDate())
                .endDate(creditFacilityDto.getEndDate())
                .build();

        CreditFacility creditFacilityCreated = creditFacilityRepository.save(creditFacilityCreation);

        saveLoanLimits(creditFacilityDto.getLoanTypeLimits(), creditFacilityCreated);
    }

    private void saveLoanLimits(List<LoanTypeLimitDto> loanTypeLimits, CreditFacility creditFacility){
        List<LoanLimit> loanLimits = new ArrayList<>();

        loanTypeLimits.forEach(item ->{
            LoanType loanType = loanTypeService.getLoanTypeById(item.getLoanTypeId());

            LoanLimit loanLimit = LoanLimit.builder().
                    creditFacility(creditFacility)
                    .loanType(loanType)
                    .limit(item.getLimit())
                    .build();
            loanLimits.add(loanLimit);
        });

        loanLimitService.saveAll(loanLimits);
    }

    @Override
    public CreditFacility getCreditFacilityById(String creditFacilityId) {
        return creditFacilityRepository.findById(creditFacilityId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find CreditFacility with CreditFacilityId {%s}", creditFacilityId)));
    }

    @Override
    public List<CreditFacility> findByApplicantId(String applicantId) {
        List<CreditFacility> creditFacilities = creditFacilityRepository.findByApplicant_Id(applicantId);

        if(CollectionUtils.isEmpty(creditFacilities)){
            return new ArrayList<>();
        }

        return creditFacilities;
    }
}
