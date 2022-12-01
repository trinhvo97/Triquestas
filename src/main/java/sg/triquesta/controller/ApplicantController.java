package sg.triquesta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.applicant.ApplicantDto;
import sg.triquesta.model.dto.response.ApiResponse;
import sg.triquesta.service.applicant.ApplicantService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class ApplicantController {
    private final ApplicantService applicantService;

    @PostMapping("/applicants")
    public ResponseEntity<HttpStatus> createApplicant(@RequestBody @Valid ApplicantDto applicantDto){
        applicantService.saveApplicant(applicantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/applicants/{id}")
    public ResponseEntity<ApiResponse<Object>> getLoansOfApplicant(
            @PathVariable("id") String applicantId
    ){
        applicantService.getApplicantByLoan(applicantId);
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .data(applicantService.getApplicantByLoan(applicantId))
                        .status(HttpStatus.OK.value()).build());
    }

}
