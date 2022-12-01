package sg.triquesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.loan.LoanLimit;

import java.util.Optional;

@Repository
public interface LoanLimitRepository extends JpaRepository<LoanLimit, String>{
    Optional<LoanLimit> findByLoanType_IdEqualsAndCreditFacility_Id(String loanTypeId, String creditFacility_id);

}
