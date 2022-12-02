package sg.triquesta.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.applicant.ApplicantDto;
import sg.triquesta.model.dto.response.applicant.ApplicantCurrentLoan;
import sg.triquesta.model.dto.response.applicant.ApplicantResponse;
import sg.triquesta.service.applicant.ApplicantService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ApplicantController {
    private final ApplicantService applicantService;

    @Operation(description = "Endpoint api for creating applicant")
    @PostMapping("/applicants")
    public ResponseEntity<HttpStatus> createApplicant(@RequestBody @Valid ApplicantDto applicantDto){
        applicantService.saveApplicant(applicantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(description = "Endpoint api that applicant can see the list of loan applicant have and payment made to the loan")
    @GetMapping("/applicants/{applicantId}")
    public ResponseEntity<ApplicantResponse> getLoansOfApplicant(
            @PathVariable("applicantId") String applicantId
    ){
        return new ResponseEntity<>(applicantService.getApplicantByLoan(applicantId), HttpStatus.OK);
    }

    @Operation(description = "Endpoint api that applicant can monitor the current total of the loans that the applicant currently has")
    @GetMapping("/applicants/{applicantId}/total-loan")
    public ResponseEntity<ApplicantCurrentLoan> getCurrentTotalLoansOfApplicant(
            @PathVariable("applicantId") String applicantId
    ){
        ApplicantCurrentLoan data = applicantService.getTotalCurrentLoan(applicantId);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
