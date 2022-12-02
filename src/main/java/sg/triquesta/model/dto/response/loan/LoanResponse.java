package sg.triquesta.model.dto.response.loan;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import sg.triquesta.model.dto.response.payment.LoanPaymentResponse;
import sg.triquesta.model.entity.enums.Currency;
import sg.triquesta.model.entity.enums.LoanStatus;
import sg.triquesta.model.entity.loan.Loan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanResponse {
    private String id;
    private BigDecimal amount;
    private BigDecimal remainAmount;
    private LoanStatus loanStatus;
    private Currency currency;
    private Double interestRate;
    private Date startDate;
    private Date endDate;
    private List<LoanPaymentResponse> loanPayments;

    public static LoanResponse fromLoan(Loan loan){
        return LoanResponse.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .remainAmount(loan.getRemainAmount())
                .loanStatus(loan.getLoanStatus())
                .currency(loan.getCurrency())
                .interestRate(loan.getInterestRate())
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .loanPayments(LoanPaymentResponse.fromLoans(loan.getLoanPayments()))
                .build();
    }

    public static List<LoanResponse> fromLoans(List<Loan> loans){
        if(CollectionUtils.isEmpty(loans)){
            return new ArrayList<>();
        }

        return loans.stream().map(LoanResponse::fromLoan).collect(Collectors.toList());
    }

}
