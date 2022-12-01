package sg.triquesta.service.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.triquesta.exception.BadRequestException;
import sg.triquesta.model.dto.request.payment.LoanPaymentDto;
import sg.triquesta.model.entity.enums.LoanStatus;
import sg.triquesta.model.entity.loan.Loan;
import sg.triquesta.model.entity.loan.LoanPayment;
import sg.triquesta.repository.LoanPaymentRepository;
import sg.triquesta.service.loan.LoanService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanPaymentServiceImpl implements LoanPaymentService{
    private final LoanService loanService;
    private final LoanPaymentRepository loanPaymentRepository;

    @Override
    @Transactional
    public void paymentOfCreditFacility(String loanId, LoanPaymentDto loanPaymentDto) {
        Loan loan = loanService.getLoanById(loanId);

        if(!Objects.equals(loan.getLoanStatus(), LoanStatus.COMPLETED)){
            throw new BadRequestException("Your loan must to approval by system.");
        }

        if(loanPaymentDto.getPaymentAmount().compareTo(loan.getRemainAmount()) > 0){
            throw new BadRequestException("Your payment amount must be less then or equal your remain amount.");
        }

        BigDecimal remainAmount = loan.getRemainAmount().subtract(loanPaymentDto.getPaymentAmount());
        LoanPayment loanPayment = LoanPayment.builder()
                .loan(loan)
                .paymentAmount(loanPaymentDto.getPaymentAmount())
                .paymentDate(new Date())
                .build();

        loan.setRemainAmount(remainAmount);

        if(remainAmount.compareTo(BigDecimal.ZERO) == 0){
            loan.setIsCompleted(true);
            loan.setLoanStatus(LoanStatus.COMPLETED);
        }else {
            loan.setLoanStatus(LoanStatus.IN_PROGRESS);
        }

        loanService.saveLoan(loan);
        loanPaymentRepository.save(loanPayment);
    }

}
