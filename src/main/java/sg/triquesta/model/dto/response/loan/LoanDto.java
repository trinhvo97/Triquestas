package sg.triquesta.model.dto.response.loan;

import lombok.*;
import org.apache.commons.collections4.CollectionUtils;
import sg.triquesta.model.dto.response.payment.LoanPaymentDto;
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
public class LoanDto {
    private String id;
    private BigDecimal amount;
    private BigDecimal remainAmount;
    private LoanStatus loanStatus;
    private Currency currency;
    private Double interestRate;
    private Date startDate;
    private Date endDate;
    private List<LoanPaymentDto> loanPayments;

    public static LoanDto fromLoan(Loan loan){
        return LoanDto.builder()
                .id(loan.getId())
                .amount(loan.getAmount())
                .remainAmount(loan.getRemainAmount())
                .loanStatus(loan.getLoanStatus())
                .currency(loan.getCurrency())
                .interestRate(loan.getInterestRate())
                .startDate(loan.getStartDate())
                .endDate(loan.getEndDate())
                .loanPayments(LoanPaymentDto.fromLoans(loan.getLoanPayments()))
                .build();
    }

    public static List<LoanDto> fromLoans(List<Loan> loans){
        if(CollectionUtils.isEmpty(loans)){
            return new ArrayList<>();
        }

        return loans.stream().map(LoanDto::fromLoan).collect(Collectors.toList());
    }

}
