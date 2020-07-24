package vn.vmg.infotrading.webapp.internal.ipAccess.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.repository.IpAccessRepository;
@Component
public class DeleteIPAccessValidator implements InternalValidator {
    @Autowired
    private IpAccessRepository ipAccessRepository ;
    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
    @Override
    public InternalResult valid(Object domain) {
        Integer id = (Integer) domain;
        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace(String.valueOf(id), "id null");
        if (id instanceof Integer) {
            IPAccess ipAccess = ipAccessRepository.getById(id);
            if (id == null) {
                result.rejectValue("Id ", "id not found");
            }
        } else {
            result.rejectIfEmptyOrWhitespace(String.valueOf(id), "invalid id");
        }
        return result;
    }
}
