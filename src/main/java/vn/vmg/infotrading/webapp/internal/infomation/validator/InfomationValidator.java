package vn.vmg.infotrading.webapp.internal.infomation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.infomation.repository.InformationRepository;

@Component
public class InfomationValidator implements InternalValidator {
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
    @Override
    public InternalResult valid(Object domain) {
        Information information = (Information) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(information.getFieldName(),"fieldName");
        result.rejectIfEmptyOrWhitespace(information.getPartnerId().toString(),"partnerId");
        result.rejectIfEmptyOrWhitespace(information.getInforGroupId().toString(),"inforGroupId");

        return result;
    }
}
