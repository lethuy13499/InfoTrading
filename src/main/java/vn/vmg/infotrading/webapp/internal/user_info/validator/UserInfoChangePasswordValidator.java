package vn.vmg.infotrading.webapp.internal.user_info.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.user_info.domain.UserInfoModel;

@Component
public class UserInfoChangePasswordValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        UserInfoModel userInfoModel = (UserInfoModel) domain;
        String oldPassword = userInfoModel.getOldPassword();
        String newPassword = userInfoModel.getNewPassword();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("oldPassword", oldPassword);
        result.rejectIfEmptyOrWhitespace("newPassword", newPassword);

        return result;
    }
}
