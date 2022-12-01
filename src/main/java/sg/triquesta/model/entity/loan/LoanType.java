package sg.triquesta.model.entity.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "LoanType")
public class LoanType extends AuditedEntity {
   // HOME OR CAR OR ...
    @Column(name = "Name")
    private String name;

}
