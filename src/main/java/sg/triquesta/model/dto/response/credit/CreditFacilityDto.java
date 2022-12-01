package sg.triquesta.model.dto.response.credit;

import lombok.*;
import sg.triquesta.model.dto.response.loan.LoanDto;
import sg.triquesta.model.entity.enums.Currency;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CreditFacilityDto {
    private String id;
    private String name;
    private Currency currency;
    private Date startDate;
    private Date endDate;
    private List<LoanDto> loans;


}
