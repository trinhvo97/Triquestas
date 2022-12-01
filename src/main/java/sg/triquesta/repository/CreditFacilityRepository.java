package sg.triquesta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.credit.CreditFacility;

import java.util.List;

@Repository
public interface CreditFacilityRepository extends JpaRepository<CreditFacility, String> {
    List<CreditFacility> findByApplicant_Id(String id);
}
