package sg.triquesta.model.dto.request.payment;

import lombok.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanPaymentDto {
    @Min(1)
    private BigDecimal paymentAmount;

}
