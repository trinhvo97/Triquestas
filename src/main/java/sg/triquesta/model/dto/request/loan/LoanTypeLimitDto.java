package sg.triquesta.model.dto.request.loan;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanTypeLimitDto {

    @NotBlank
    @NotEmpty(message = "loanTypeId name cannot be empty.")
    private String loanTypeId;

    @Min(1)
    private BigDecimal limit;
}
