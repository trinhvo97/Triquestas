package sg.triquesta.model.dto.request.loan;

import lombok.*;
import sg.triquesta.model.entity.enums.Currency;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanDto {

    @Min(1)
    private BigDecimal amount;

    @Min(1)
    private BigDecimal remainAmount;

    private Boolean isCompleted;

    @NotBlank
    @NotEmpty(message = "currency cannot be empty.")
    private Currency currency;

    @DecimalMin("0.0")
    private Double interestRate;

    @NonNull
    private Date startDate;

    @NonNull
    private Date endDate;

    @NonNull
    private String loanTypeId;
}
