package sg.triquesta.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.triquesta.model.dto.request.payment.LoanPaymentDto;
import sg.triquesta.service.payment.LoanPaymentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class PaymentController {
    private final LoanPaymentService loanPaymentService;

    @Operation(description = "Endpoint api that applicant can pay the loan fully/partially")
    @PostMapping("/payment/{loanId}/loan")
    public ResponseEntity<HttpStatus> createPaymentOfApplicant(
            @PathVariable("loanId") String loanId,
            @RequestBody @Valid LoanPaymentDto loanPayment){

        loanPaymentService.paymentOfCreditFacility(loanId, loanPayment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
