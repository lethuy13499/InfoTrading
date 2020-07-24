package vn.vmg.infotrading.webapp.internal.sharing_partner.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartner;
import vn.vmg.infotrading.webapp.repository.SharingPartnerOracleRepository;

@Component
public class SharingPartnerDeleteValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Autowired
    private SharingPartnerOracleRepository sharingPartnerRepository;

    @Override
    public InternalResult valid(Object domain) {
        Long id = (Long) domain;

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("id", id);

        SharingPartner oldSharingPartner = sharingPartnerRepository.getById(id);
        if (oldSharingPartner == null) {
            result.rejectIfRecordChange("delete");
        }

        return result;
    }
}
