package vn.vmg.infotrading.webapp.internal.auth.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.common.Constants;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;
import vn.vmg.infotrading.webapp.internal.auth.validator.ChangePasswordValidator;
import vn.vmg.infotrading.webapp.internal.auth.validator.CheckCodeValidator;
import vn.vmg.infotrading.webapp.internal.auth.validator.GetCodeValidator;
import vn.vmg.infotrading.webapp.internal.auth.validator.LoginValidator;
import vn.vmg.infotrading.webapp.repository.AuthAPI;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

@Service
public class AuthService {

    @Autowired
    private AuthAPI authAPI;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private GetCodeValidator getCodeValidator;

    @Autowired
    private CheckCodeValidator checkCodeValidator;

    @Autowired
    private ChangePasswordValidator changePasswordValidator;

    public RxResult login(HeaderModel headerModel, AuthModel authModel) {
        InternalResult internalResult = loginValidator.valid(authModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject result;
        switch (authModel.getLoginType()) {
            case Constants.LoginType.NORMAL:
                result = authAPI.login(headerModel, authModel);
                break;
            case Constants.LoginType.OTP:
                result = authAPI.otpLogin(headerModel, authModel);
                break;
            case Constants.LoginType.TOTP:
                result = authAPI.totpLogin(headerModel, authModel);
                break;
            default:
                result = null;
        }

        if (result == null || !result.getLong("errorCode").equals(1L)) {
            return ApiResponseUtils.buildFailResponse("Login fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Login successful", result);
    }

    public RxResult getCode(HeaderModel headerModel, AuthModel authModel) {
       InternalResult internalResult = getCodeValidator.valid(authModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject result = authAPI.getCode(headerModel, authModel);
        if (result == null || !result.getLong("errorCode").equals(1L)) {
            return ApiResponseUtils.buildFailResponse("Get code fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Get code successful", result);
    }

    public RxResult checkCode(HeaderModel headerModel, AuthModel authModel) {
        InternalResult internalResult = checkCodeValidator.valid(authModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject result = authAPI.checkCode(headerModel, authModel);
        if (result == null || !result.getLong("errorCode").equals(1L)) {
            return ApiResponseUtils.buildFailResponse("Check code fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Check code successful", result);
    }

    public RxResult changePassword(HeaderModel headerModel, AuthModel authModel) {
        InternalResult internalResult = changePasswordValidator.valid(authModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject result = authAPI.changePassword(headerModel, authModel);
        if (result == null || !result.getLong("errorCode").equals(1L)) {
            return ApiResponseUtils.buildFailResponse("Change password fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Change password successful", result);
    }
}