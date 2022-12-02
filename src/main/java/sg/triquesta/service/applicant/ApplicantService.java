package sg.triquesta.service.applicant;

import sg.triquesta.model.dto.request.applicant.ApplicantDto;
import sg.triquesta.model.dto.response.applicant.ApplicantCurrentLoan;
import sg.triquesta.model.dto.response.applicant.ApplicantResponse;
import sg.triquesta.model.dto.response.applicant.ApplicantResponses;
import sg.triquesta.model.entity.applicant.Applicant;
import sg.triquesta.model.filter.BaseFilter;

public interface ApplicantService {
    void saveApplicant(ApplicantDto applicantDto);
    ApplicantResponses getApplicants(BaseFilter baseFilter);
    Applicant getApplicantById(String applicantId);
    ApplicantResponse getApplicantByLoan(String applicantId);

    ApplicantCurrentLoan getTotalCurrentLoan(String applicationId);
}
