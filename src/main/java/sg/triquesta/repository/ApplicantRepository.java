package sg.triquesta.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.triquesta.model.entity.applicant.Applicant;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {

    Page<Applicant> findByNameApplicantLikeOrEmailLike(String nameApplicant, String email, Pageable pageable);
}
