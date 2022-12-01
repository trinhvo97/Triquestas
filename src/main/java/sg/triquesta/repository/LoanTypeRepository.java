package sg.triquesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.loan.LoanType;

@Repository
public interface LoanTypeRepository extends JpaRepository<LoanType, String> {

}
