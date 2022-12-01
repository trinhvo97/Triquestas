package sg.triquesta.model.entity.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.entity.enums.Currency;
import sg.triquesta.model.entity.loan.Loan;
import sg.triquesta.model.entity.loan.LoanLimit;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "CreditFacility")
public class CreditFacility extends AuditedEntity {

    @Column(name = "TotalLimit")
    private BigDecimal totalLimit;

    @Column(name = "Currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndDate")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name ="ApplicantId")
    private Applicant applicant;

    @OneToMany(mappedBy = "creditFacility", cascade = CascadeType.ALL)
    private List<LoanLimit> loanLimits;

    @OneToMany(mappedBy = "creditFacility", cascade = CascadeType.ALL)
    private List<Loan> loans;
}
