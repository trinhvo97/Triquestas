package sg.triquesta.service.loan;

import sg.triquesta.model.dto.request.loan.LoanDto;
import sg.triquesta.model.dto.request.loan.LoanModifyDto;
import sg.triquesta.model.entity.loan.Loan;

public interface LoanService {
    void createLoan(String creditFacilityId, LoanDto loanDto);

    Loan getLoanById(String loanId);

    void updateLoan(String loanId, LoanModifyDto loanModify);

    void saveLoan(Loan loan);
}
