package vn.vmg.infotrading.webapp.internal.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalConstant;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;

@Component
public class GetCodeValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        AuthModel authModel = (AuthModel) domain;
        String username = authModel.getUsername();
        String email = authModel.getEmail();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("username", username);
        result.rejectIfInvalidMaxLength("username", username, 100);
        result.rejectIfWrongRegex("username", username, InternalConstant.USERNAME_PATTERN);
        result.rejectIfEmptyOrWhitespace("email", email);
        result.rejectIfInvalidMaxLength("email", email, 100);
        result.rejectIfWrongRegex("email", email, InternalConstant.EMAIL_PATTERN);

        return result;
    }
}
