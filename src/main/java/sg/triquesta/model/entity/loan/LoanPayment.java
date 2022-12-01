package sg.triquesta.model.entity.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "LoanPayment")
public class LoanPayment extends AuditedEntity {

    @Column(name = "PaymentAmount")
    private BigDecimal paymentAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PaymentDate")
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name ="LoanId")
    private Loan loan;
}
