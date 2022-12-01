package sg.triquesta.model.dto.request.loan;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanTypeDto {

    @NotBlank
    @NotEmpty(message = "Loan type name cannot be empty.")
    private String name;
}
