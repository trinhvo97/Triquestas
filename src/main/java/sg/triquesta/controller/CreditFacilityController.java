package sg.triquesta.controller;

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

    @PostMapping("/applicants/{id}/credit-facilities")
    public ResponseEntity<HttpStatus> createCreditFacility(
            @PathVariable("id") String applicantId,
            @RequestBody @Valid CreditFacilityDto creditFacility){

        creditFacilityService.createCreditFacility(applicantId, creditFacility);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
