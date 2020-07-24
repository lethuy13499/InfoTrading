package vn.vmg.infotrading.webapp.internal.InfomationGroup.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.config.InfomationGroupMessage;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroupSearch;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.repository.InfomationGroupRepository;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;

@Component
public class SearchInfoGroupValidator implements InternalValidator {
    @Autowired
    private InfomationGroupRepository infomationGroupRepository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
    @Autowired
    private InfomationGroupMessage infomationGroupMessage;

    @Override
    public InternalResult valid(Object domain) {
        InformationGroupSearch params = (InformationGroupSearch) domain;
        InternalResult internalResult = new InternalResult(commonErrorsMsg);
        infoGroupValidLength("name", params.getCode(), 16, infomationGroupMessage.getSearchCode(), internalResult);
        if (params.getStatus() != null && (params.getStatus() != 0 && params.getStatus() != 1)) {
            internalResult.rejectValue("status", infomationGroupMessage.getSearchStatus());
        }
        return internalResult;
    }
    private InternalResult infoGroupValidLength(String key, String value, Integer length, String message, InternalResult result) {
        if ((value != null && !value.isEmpty()) && value.length() > length) {
            result.rejectValue(key, key + message + length);
        }
        return result;
    }

}
