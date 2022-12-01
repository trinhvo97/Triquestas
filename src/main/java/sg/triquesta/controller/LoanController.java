package sg.triquesta.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.loan.LoanDto;
import sg.triquesta.service.loan.LoanService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LoanController {
    private final LoanService loanService;

    @Operation(description = "Endpoint api for creating loan of applicant")
    @PostMapping("/loans/{creditFacilityId}/credit-facilities")
    public ResponseEntity<HttpStatus> createLoan(
            @PathVariable("creditFacilityId") String creditFacilityId,
            @RequestBody @Valid LoanDto creditFacility){

        loanService.createLoan(creditFacilityId, creditFacility);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
