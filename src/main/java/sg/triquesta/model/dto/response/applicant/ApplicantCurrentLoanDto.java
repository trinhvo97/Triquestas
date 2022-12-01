package sg.triquesta.model.dto.response.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ApplicantCurrentLoanDto {
    private BigDecimal totalAmount;

    private BigDecimal totalPaymentAmount;

    private BigDecimal totalRemainAmount;

}
