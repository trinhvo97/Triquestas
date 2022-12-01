package sg.triquesta.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.credit.CreditFacilityDto;
import sg.triquesta.service.credit.CreditFacilityService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class CreditFacilityController {
    private final CreditFacilityService creditFacilityService;

    @Operation(description = "Endpoint api for creating credit facilities of applicant")
    @PostMapping("/credit-facilities/{applicantId}/applicants")
    public ResponseEntity<HttpStatus> createCreditFacility(
            @PathVariable("applicantId") String applicantId,
            @RequestBody @Valid CreditFacilityDto creditFacility){

        creditFacilityService.createCreditFacility(applicantId, creditFacility);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
