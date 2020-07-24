package vn.vmg.infotrading.webapp.internal.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalConstant;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;

@Component
public class ChangePasswordValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        AuthModel authModel = (AuthModel) domain;
        String username = authModel.getUsername();
        String code = authModel.getCode();
        String newPassword = authModel.getNewPassword();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("username", username);
        result.rejectIfWrongRegex("username", username, InternalConstant.USERNAME_PATTERN);
        result.rejectIfEmptyOrWhitespace("code", code);
        result.rejectIfEmptyOrWhitespace("newPassword", newPassword);

        return result;
    }
}
