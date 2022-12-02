package sg.triquesta.model.dto.response.applicant;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;
import sg.triquesta.model.dto.response.credit.CreditFacilityResponse;
import sg.triquesta.model.entity.applicant.Applicant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ApplicantResponse {
    private String id;

    private String name;

    private String email;

    private String address;

    private String mobile;

    private List<CreditFacilityResponse> creditFacilities;

    public static ApplicantResponse fromApplicant(Applicant applicant, List<CreditFacilityResponse> creditFacilities){
        return ApplicantResponse.builder()
                .id(applicant.getId())
                .name(applicant.getNameApplicant())
                .email(applicant.getEmail())
                .mobile(applicant.getMobile())
                .creditFacilities(creditFacilities)
                .build();
    }

    public static ApplicantResponse fromApplicant(Applicant applicant){
        return ApplicantResponse.builder()
                .id(applicant.getId())
                .name(applicant.getNameApplicant())
                .email(applicant.getEmail())
                .mobile(applicant.getMobile())
                .build();
    }

    public static List<ApplicantResponse> fromApplicants(List<Applicant> applicants){
        if(CollectionUtils.isEmpty(applicants)){
            return new ArrayList<>();
        }

        return applicants.stream()
                .map(ApplicantResponse::fromApplicant)
                .collect(Collectors.toList());
    }
}
