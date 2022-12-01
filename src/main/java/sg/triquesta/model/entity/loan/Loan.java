package sg.triquesta.model.entity.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;
import sg.triquesta.model.entity.credit.CreditFacility;
import sg.triquesta.model.entity.enums.Currency;
import sg.triquesta.model.entity.enums.LoanStatus;

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
@Table(name = "Loan")
public class Loan extends AuditedEntity {

    @Column(name = "Amount")
    private BigDecimal amount;

    @Column(name = "RemainAmount")
    private BigDecimal remainAmount;

    @Column(name = "IsCompleted")
    private Boolean isCompleted;

    @Column(name = "Currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "InterestRate")
    private Double interestRate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDate")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndDate")
    private Date endDate;

    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL)
    private List<LoanPayment> loanPayments;

    @Column(name = "LoanStatus")
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @ManyToOne
    @JoinColumn(name ="CreditFacilityId")
    private CreditFacility creditFacility;

    @ManyToOne
    @JoinColumn(name ="LoanTypeId")
    private LoanType loanType;

}
