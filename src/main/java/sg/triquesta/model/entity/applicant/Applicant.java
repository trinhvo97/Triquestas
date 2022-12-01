package sg.triquesta.model.entity.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.entity.AuditedEntity;
import sg.triquesta.model.entity.credit.CreditFacility;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "Applicant")
public class Applicant extends AuditedEntity {

    @Column(name = "Name")
    private String nameApplicant;

    @Email
    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Size(max = 20)
    @Column(name = "Mobile")
    private String mobile;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<CreditFacility> creditFacilities;

}
