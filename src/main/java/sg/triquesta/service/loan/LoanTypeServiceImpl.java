package sg.triquesta.service.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import sg.triquesta.model.dto.request.loan.LoanTypeDto;
import sg.triquesta.model.entity.loan.LoanType;
import sg.triquesta.repository.LoanTypeRepository;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService{
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public void saveLoanType(LoanTypeDto loanTypeDto) {
        LoanType loanType =
                LoanType.builder()
                .name(loanTypeDto.getName()).build();

        loanTypeRepository.save(loanType);
    }

    @Override
    public LoanType getLoanTypeById(String loanTypeId) {
        return loanTypeRepository.findById(loanTypeId)
                .orElseThrow(() -> new NotFoundException(String.format("Don't find LoanType with LoanTypeId {%s}", loanTypeId)));
    }
}
