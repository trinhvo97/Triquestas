package sg.triquesta.service.loan;
import sg.triquesta.model.entity.loan.LoanLimit;

import java.util.List;

public interface LoanLimitService{
    LoanLimit getLoanLimit(String loanType, String creditFacilityId);

    void saveAll(List<LoanLimit> loanLimits);
}
