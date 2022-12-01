package sg.triquesta.service.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sg.triquesta.exception.BadRequestException;
import sg.triquesta.model.dto.request.loan.LoanDto;
import sg.triquesta.model.dto.request.loan.LoanModifyDto;
import sg.triquesta.model.entity.credit.CreditFacility;
import sg.triquesta.model.entity.enums.LoanStatus;
import sg.triquesta.model.entity.loan.Loan;
import sg.triquesta.model.entity.loan.LoanLimit;
import sg.triquesta.model.entity.loan.LoanType;
import sg.triquesta.repository.LoanRepository;
import sg.triquesta.service.credit.CreditFacilityService;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final CreditFacilityService creditFacilityService;
    private final LoanTypeService loanTypeService;
    private final LoanLimitService loanLimitService;
    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public void createLoan(String creditFacilityId, LoanDto loanDto) {
        CreditFacility creditFacility = creditFacilityService.getCreditFacilityById(creditFacilityId);
        LoanType loanType = loanTypeService.getLoanTypeById(loanDto.getLoanTypeId());
        LoanLimit loanLimit = loanLimitService.getLoanLimit(loanType.getId(), creditFacility.getId());

        if(loanDto.getAmount().compareTo(loanLimit.getLimit()) > 0){
            throw new BadRequestException("Your amount greater than limit of credit facility");
        }

        Loan loanCreation = Loan.builder()
                .isCompleted(false)
                .remainAmount(loanDto.getAmount())
                .amount(loanDto.getAmount())
                .currency(loanDto.getCurrency())
                .interestRate(loanDto.getInterestRate())
                .startDate(loanDto.getStartDate())
                .endDate(loanDto.getEndDate())
                .loanType(loanType)
                .loanStatus(LoanStatus.NEW)
                .creditFacility(creditFacility)
                .build();

        loanRepository.save(loanCreation);
    }

    @Override
    public Loan getLoanById(String loanId) {
        return loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find Loan with loanId {%s}",loanId)));
    }

    @Override
    public void saveLoan(Loan loan) {
        loanRepository.save(loan);
    }
}
