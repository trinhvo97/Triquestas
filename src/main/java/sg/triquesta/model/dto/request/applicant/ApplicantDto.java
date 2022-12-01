package sg.triquesta.model.dto.request.applicant;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApplicantDto {
    private String name;

    @Email
    private String email;

    private String address;

    @Size(max = 12)
    private String mobile;
}
