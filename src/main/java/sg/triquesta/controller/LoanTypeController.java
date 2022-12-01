package sg.triquesta.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.loan.LoanTypeDto;
import sg.triquesta.service.loan.LoanTypeService;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class LoanTypeController {

    private final LoanTypeService loanTypeService;

    @Operation(description = "Endpoint api for creating loan type like HOME, CAR or ...")
    @PostMapping("/loan-types")
    public ResponseEntity<HttpStatus> createLoanType(
            @RequestBody @Valid LoanTypeDto loanTypeDto){

        loanTypeService.saveLoanType(loanTypeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
