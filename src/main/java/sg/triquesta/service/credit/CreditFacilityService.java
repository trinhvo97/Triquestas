package sg.triquesta.service.credit;

import sg.triquesta.model.dto.request.credit.CreditFacilityDto;
import sg.triquesta.model.entity.credit.CreditFacility;

import java.util.List;

public interface CreditFacilityService {
    void createCreditFacility(String applicantId, CreditFacilityDto creditFacility);

    CreditFacility getCreditFacilityById(String creditFacilityId);

    List<CreditFacility> findByApplicantId(String id);
}
