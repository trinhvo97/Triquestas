package sg.triquesta.model.entity.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;
import sg.triquesta.model.entity.credit.CreditFacility;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "LoanLimit")
public class LoanLimit extends AuditedEntity {

    @ManyToOne
    @JoinColumn(name ="CreditFacilityId", nullable = false)
    private CreditFacility creditFacility;

    @ManyToOne
    @JoinColumn(name ="LoanTypeId", nullable = false)
    private LoanType loanType;

    @Column(name = "LoanLimit")
    private BigDecimal limit;
}
