package sg.triquesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.loan.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, String> {

}
