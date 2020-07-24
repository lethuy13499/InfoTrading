package vn.vmg.infotrading.webapp.internal.user_info.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.user_info.domain.UserInfoModel;
import vn.vmg.infotrading.webapp.internal.user_info.validator.UserInfoChangePasswordValidator;
import vn.vmg.infotrading.webapp.internal.user_info.validator.UserInfoUpdateValidator;
import vn.vmg.infotrading.webapp.repository.GatewayAPI;
import vn.vmg.infotrading.webapp.repository.UserInfoAPI;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

@Service
public class UserInfoService {

    @Autowired
    private GatewayAPI gatewayAPI;

    @Autowired
    private UserInfoAPI userInfoAPI;

    @Autowired
    private UserInfoUpdateValidator userInfoUpdateValidator;

    @Autowired
    private UserInfoChangePasswordValidator userInfoChangePasswordValidator;

    public RxResult get(HeaderModel headerModel, UserInfoModel userInfoModel) {
        JsonObject jwtInfo = gatewayAPI.checkJwt(headerModel);
        if (jwtInfo == null) {
            return ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
        }

        headerModel.setCurrentuserid(jwtInfo.getLong("userId"));

        JsonObject result = userInfoAPI.get(headerModel, userInfoModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Get user info fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Get user info successful", result);
    }

    public RxResult update(HeaderModel headerModel, UserInfoModel userInfoModel) {
        JsonObject jwtInfo = gatewayAPI.checkJwt(headerModel);
        if (jwtInfo == null) {
            return ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
        }

        headerModel.setCurrentuserid(jwtInfo.getLong("userId"));

        InternalResult internalResult = userInfoUpdateValidator.valid(userInfoModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject result = userInfoAPI.update(headerModel, userInfoModel);
        if (result == null || !result.getLong("resData").equals(200L)) {
            return ApiResponseUtils.buildFailResponse("Update user info fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Update user info successful", result);
    }

    public RxResult getMenu(HeaderModel headerModel, UserInfoModel userInfoModel) {
        JsonArray result = userInfoAPI.getMenu(headerModel, userInfoModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Get menu fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Get menu successful", result);
    }

    public RxResult changePassword(HeaderModel headerModel, UserInfoModel userInfoModel) {
        InternalResult internalResult = userInfoChangePasswordValidator.valid(userInfoModel);
        if (internalResult.hasErrors()) {
            return ApiResponseUtils.buildBadRequestResponse(internalResult.getErrors());
        }

        JsonObject jwtInfo = gatewayAPI.checkJwt(headerModel);
        if (jwtInfo == null) {
            return ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
        }

        headerModel.setCurrentuserid(jwtInfo.getLong("userId"));

        JsonObject result = userInfoAPI.changePassword(headerModel, userInfoModel);
        if (result == null || !result.getLong("resData").equals(1L)) {
            return ApiResponseUtils.buildFailResponse("Change password fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Change password successful", result);
    }

    public RxResult logout(HeaderModel headerModel, UserInfoModel userInfoModel) {
        JsonObject result = userInfoAPI.logout(headerModel, userInfoModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Logout fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Logout successful", result);
    }
}