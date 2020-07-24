package vn.vmg.infotrading.webapp.internal.user_info.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalConstant;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.user_info.domain.UserInfoModel;
import vn.vmg.infotrading.webapp.utils.StringUtils;

@Component
public class UserInfoUpdateValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;

    @Override
    public InternalResult valid(Object domain) {
        UserInfoModel userInfoModel = (UserInfoModel) domain;
        String dob = userInfoModel.getDob();
        String email = userInfoModel.getEmail();
        String phone = userInfoModel.getPhone();
        String landlinePhone = userInfoModel.getLandlinePhone();

        InternalResult result = new InternalResult(commonErrorsMsg);
        if (!StringUtils.isNullOrEmpty(dob)) {
            result.rejectIfWrongRegex("dob", dob, InternalConstant.YYYY_MM_DD_PATTERN);
        }

        if (!StringUtils.isNullOrEmpty(email)) {
            result.rejectIfInvalidMaxLength("email", email, 100);
            result.rejectIfWrongRegex("email", email, InternalConstant.EMAIL_PATTERN);
        }

        if (!StringUtils.isNullOrEmpty(phone)) {
            result.rejectIfInvalidMaxLength("phone", phone, 20);
            result.rejectIfWrongRegex("phone", phone, InternalConstant.PHONE_PATTERN);
        }

        if (!StringUtils.isNullOrEmpty(landlinePhone)) {
            result.rejectIfInvalidMaxLength("landlinePhone", landlinePhone, 20);
            result.rejectIfWrongRegex("landlinePhone", landlinePhone, InternalConstant.PHONE_PATTERN);
        }
        
        return result;
    }
}
