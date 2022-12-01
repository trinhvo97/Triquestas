package sg.triquesta.model.dto.request.loan;

import lombok.*;
import sg.triquesta.model.entity.enums.LoanStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoanModifyDto {

    @NonNull
    private LoanStatus loanStatus;
}
