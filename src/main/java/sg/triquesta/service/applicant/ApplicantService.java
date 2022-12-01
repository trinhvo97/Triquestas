package sg.triquesta.service.applicant;

import sg.triquesta.model.dto.request.applicant.ApplicantDto;
import sg.triquesta.model.dto.response.applicant.ApplicantCurrentLoanDto;
import sg.triquesta.model.dto.response.applicant.ApplicantResponseDto;
import sg.triquesta.model.dto.response.applicant.ApplicantResponses;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.filter.BaseFilter;

public interface ApplicantService {
    void saveApplicant(ApplicantDto applicantDto);
    ApplicantResponses getApplicants(BaseFilter baseFilter);
    Applicant getApplicantById(String applicantId);
    ApplicantResponseDto getApplicantByLoan(String applicantId);

    ApplicantCurrentLoanDto getTotalCurrentLoan(String applicationId);
}
