package sg.triquesta.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.loan.LoanDto;
import sg.triquesta.model.dto.request.loan.LoanModifyDto;
import sg.triquesta.service.loan.LoanService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/credit-facilities/{id}/loans")
    public ResponseEntity<HttpStatus> createLoan(
            @PathVariable("id") String creditFacilityId,
            @RequestBody @Valid LoanDto creditFacility){

        loanService.createLoan(creditFacilityId, creditFacility);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/loans/{id}")
    public ResponseEntity<HttpStatus> createUpdateLoan(
            @PathVariable("id") String loanId,
            @RequestBody @Valid LoanModifyDto creditFacility){

        loanService.updateLoan(loanId, creditFacility);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
