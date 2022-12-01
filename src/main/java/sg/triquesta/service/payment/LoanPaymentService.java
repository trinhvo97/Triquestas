package sg.triquesta.service.payment;

import sg.triquesta.model.dto.request.payment.LoanPaymentDto;

public interface LoanPaymentService {
    void paymentOfCreditFacility(String loanId, LoanPaymentDto loanPayment);
}
