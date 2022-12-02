package sg.triquesta.model.dto.request.credit;

import lombok.*;
import sg.triquesta.model.dto.request.loan.LoanTypeLimitDto;
import sg.triquesta.model.entity.enums.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreditFacilityDto {

    @Min(1)
    private BigDecimal totalLimit;

    @NotBlank
    @NotEmpty(message = "currency cannot be empty.")
    private String currency;

    @NonNull
    private Date startDate;

    @NonNull
    private Date endDate;

    @NotEmpty
    List<LoanTypeLimitDto> loanTypeLimits;
}
