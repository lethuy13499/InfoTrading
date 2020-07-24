package vn.vmg.infotrading.webapp.internal.auth.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.common.Constants;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;
import vn.vmg.infotrading.webapp.internal.InternalConstant;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.InternalValidator;
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;

@Component
public class LoginValidator implements InternalValidator {

    @Autowired
    private CommonErrorsMsg commonErrorsMsg;
//    public Single<WebAppError> isValid(AuthModel authModel) {
//        WebAppError error = new WebAppError();
//        error.rejectIfEmptyOrWhitespace("username", authModel., "Mã ERROR  message ERROR for id");
//        error.rejectIfEmptyOrWhitespace("username", partnerModel.getUsername(), "Mã ERROR  message ERROR for username");
//        error.rejectIfEmptyOrWhitespace("otherInfo", partnerModel.getOtherInfo(), "Mã ERROR  message ERROR for otherInfo");
//        if (!error.hasFieldErrors("otherInfo")) {
//            if (partnerModel.getOtherInfo() < 0) {
//                error.rejectValue("otherInfo", "Mã ERROR  message ERROR for otherInfo");
//            }
//        }
//        PartnerResponse partner = repository.findById(partnerModel.getId());
//        if (partner != null) {
//            error.rejectValue("id", "Mã ERROR  message ERROR for id EXISTS");
//        }
//
//        return Single.just(error);
//    }

    @Override
    public InternalResult valid(Object domain) {
        AuthModel authModel = (AuthModel) domain;
        String username = authModel.getUsername();
        String password = authModel.getPassword();
        String loginType = authModel.getLoginType();
        String otp = authModel.getOtp();
        String totp = authModel.getTotp();

        InternalResult result = new InternalResult(commonErrorsMsg);
        result.rejectIfEmptyOrWhitespace("username", username);
        result.rejectIfInvalidMaxLength("username", username, 100);
        result.rejectIfWrongRegex("username", username, InternalConstant.USERNAME_PATTERN);
        result.rejectIfEmptyOrWhitespace("password", password);
        result.rejectIfEmptyOrWhitespace("loginType", loginType);

        if (loginType != null) {
            if (!loginType.equals(Constants.LoginType.NORMAL) &&
                    !loginType.equals(Constants.LoginType.OTP) &&
                    !loginType.equals(Constants.LoginType.TOTP)) {
                result.rejectInvalidValue("loginType");
            }

            if (loginType.equals(Constants.LoginType.OTP)) {
                result.rejectIfEmptyOrWhitespace("otp", otp);
            } else if (loginType.equals(Constants.LoginType.TOTP)) {
                result.rejectIfEmptyOrWhitespace("totp", totp);
            }
        }



        return result;
    }
}
