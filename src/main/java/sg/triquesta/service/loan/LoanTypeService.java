package sg.triquesta.service.loan;

import sg.triquesta.model.dto.request.loan.LoanTypeDto;
import sg.triquesta.model.entity.loan.LoanType;

public interface LoanTypeService {
    void saveLoanType(LoanTypeDto loanTypeDto);

    LoanType getLoanTypeById(String loanTypeId);
}
