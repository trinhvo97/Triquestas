package sg.triquesta.model.dto.response.applicant;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;
import sg.triquesta.model.dto.response.credit.CreditFacilityDto;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.entity.credit.CreditFacility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ApplicantResponseDto {
    private String id;

    private String name;

    private String email;

    private String address;

    private String mobile;

    private List<CreditFacilityDto> creditFacilities;

    public static ApplicantResponseDto fromApplicant(Applicant applicant){
        return ApplicantResponseDto.builder()
                .id(applicant.getId())
                .name(applicant.getNameApplicant())
                .email(applicant.getEmail())
                .mobile(applicant.getMobile())
                .build();
    }

    public static List<ApplicantResponseDto> fromApplicants(List<Applicant> applicants){
        if(CollectionUtils.isEmpty(applicants)){
            return new ArrayList<>();
        }

        return applicants.stream()
                .map(ApplicantResponseDto::fromApplicant)
                .collect(Collectors.toList());
    }
}
