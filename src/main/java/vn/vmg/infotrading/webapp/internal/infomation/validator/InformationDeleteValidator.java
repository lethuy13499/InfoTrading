package vn.vmg.infotrading.webapp.internal.infomation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.infomation.repository.InformationRepository;

@Component
public class InformationDeleteValidator implements InternalValidator {
    @Autowired
    private InformationRepository repository;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        Integer id = (Integer) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(String.valueOf(id), "id null");
        if (id instanceof Integer) {
            Information information = repository.findById(id);
            if (information.getId() == null) {
                result.rejectValue("Id ", "id not found");
            }
        } else {
            result.rejectIfEmptyOrWhitespace(String.valueOf(id), "invalid id");
        }
        return result;
    }
}