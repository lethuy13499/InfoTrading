package vn.vmg.infotrading.webapp.internal.sharing_partner.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartnerModel;
import vn.vmg.infotrading.webapp.utils.StringUtils;

@Component
public class SharingPartnerInsertValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        SharingPartnerModel sharingPartnerModel = (SharingPartnerModel) domain;
        Integer receiveRate = sharingPartnerModel.getReceiveRate();
        String note = sharingPartnerModel.getNote();
        Long partnerId = sharingPartnerModel.getPartnerId();
        Long appendixId = sharingPartnerModel.getAppendixId();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("receiveRate", receiveRate);
        result.rejectIfEmptyOrWhitespace("partnerId", partnerId);
        result.rejectIfEmptyOrWhitespace("appendixId", appendixId);

        if (!StringUtils.isNullOrEmpty(note)) {
            result.rejectIfInvalidMaxLength("note", note, 500);
        }

        return result;
    }
}
