package vn.vmg.infotrading.webapp.internal.sharing_partner.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartner;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartnerModel;
import vn.vmg.infotrading.webapp.repository.SharingPartnerOracleRepository;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;

@Component
public class SharingPartnerUpdateValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Autowired
    private SharingPartnerOracleRepository sharingPartnerRepository;

    @Override
    public InternalResult valid(Object domain) {
        SharingPartnerModel sharingPartnerModel = (SharingPartnerModel) domain;
        Long id = sharingPartnerModel.getId();
        Integer receiveRate = sharingPartnerModel.getReceiveRate();
        String note = sharingPartnerModel.getNote();
        Timestamp updateTime = sharingPartnerModel.getUpdateTime();
        Long partnerId = sharingPartnerModel.getPartnerId();
        Long appendixId = sharingPartnerModel.getAppendixId();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("id", id);
        result.rejectIfEmptyOrWhitespace("receiveRate", receiveRate);
        result.rejectIfEmptyOrWhitespace("partnerId", partnerId);
        result.rejectIfEmptyOrWhitespace("appendixId", appendixId);

        if (!StringUtils.isNullOrEmpty(note)) {
            result.rejectIfInvalidMaxLength("note", note, 500);
        }

        SharingPartner oldSharingPartner = sharingPartnerRepository.getById(id);
        if (!oldSharingPartner.getUpdateTime().equals(updateTime)) {
            result.rejectIfRecordChange("update");
        }

        return result;
    }
}
