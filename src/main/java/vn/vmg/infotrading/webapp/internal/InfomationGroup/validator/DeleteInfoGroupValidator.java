package vn.vmg.infotrading.webapp.internal.InfomationGroup.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.config.InfomationGroupMessage;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.repository.InfomationGroupRepository;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;

@Component
public class DeleteInfoGroupValidator implements InternalValidator {
    @Autowired
    private InfomationGroupRepository repository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
    @Autowired
    private InfomationGroupMessage infomationGroupMessage;

    @Override
    public InternalResult valid(Object domain) {
        Integer id = (Integer) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(String.valueOf(id), "id null");
        if (id instanceof Integer) {
            InformationGroup informationGroup = repository.findById(id);
            if (informationGroup.getId() == null) {
                result.rejectValue("Id ", "id not found");
            }
        } else {
            result.rejectIfEmptyOrWhitespace(String.valueOf(id), "invalid id");
        }
        return result;
    }
}
