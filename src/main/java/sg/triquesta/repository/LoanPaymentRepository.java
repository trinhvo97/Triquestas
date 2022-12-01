package sg.triquesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.loan.LoanPayment;

@Repository
public interface LoanPaymentRepository extends JpaRepository<LoanPayment, String> {

}
