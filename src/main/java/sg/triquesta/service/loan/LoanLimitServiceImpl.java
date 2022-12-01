package sg.triquesta.service.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sg.triquesta.model.entity.loan.LoanLimit;
import sg.triquesta.repository.LoanLimitRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanLimitServiceImpl implements LoanLimitService{
    private final LoanLimitRepository loanLimitRepository;

    @Override
    public LoanLimit getLoanLimit(String loanTypeId, String creditFacilityId) {
        return  loanLimitRepository.findByLoanType_IdEqualsAndCreditFacility_Id(loanTypeId, creditFacilityId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find LoanLimit with loanTypeId {%s} or CreditFacilityId {%s}", loanTypeId, creditFacilityId)));

    }

    @Override
    public void saveAll(List<LoanLimit> loanLimits) {
        loanLimitRepository.saveAll(loanLimits);
    }
}
