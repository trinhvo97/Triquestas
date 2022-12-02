package sg.triquesta.model.dto.response.applicant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sg.triquesta.model.dto.response.PaginationResponseDto;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class ApplicantResponses extends PaginationResponseDto {
    List<ApplicantResponse> applicants;
}
