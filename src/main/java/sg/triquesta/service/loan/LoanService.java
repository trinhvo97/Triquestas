package sg.triquesta.service.loan;

import sg.triquesta.model.dto.request.loan.LoanDto;
import sg.triquesta.model.entity.loan.Loan;

import java.util.List;

public interface LoanService {
    void createLoan(String creditFacilityId, LoanDto loanDto);

    Loan getLoanById(String loanId);

    void saveLoan(Loan loan);

    List<Loan> getLoanByCreditFacilityIds(List<String> creditFacilityIds);
}
