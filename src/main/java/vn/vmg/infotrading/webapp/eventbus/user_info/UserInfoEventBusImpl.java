package vn.vmg.infotrading.webapp.eventbus.user_info;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.user_info.domain.UserInfoModel;
import vn.vmg.infotrading.webapp.internal.user_info.service.UserInfoService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;

public class UserInfoEventBusImpl implements UserInfoEventBus {

    private final UserInfoService userInfoService;

    private final GatewayService gatewayService;

    public UserInfoEventBusImpl(UserInfoService userInfoService, GatewayService gatewayService, Handler<AsyncResult<UserInfoEventBus>> readyHandler) {
        this.userInfoService = userInfoService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public UserInfoEventBus get(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            UserInfoModel userInfoModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), UserInfoModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = userInfoService.get(headerModel, userInfoModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get user info fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public UserInfoEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            UserInfoModel userInfoModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), UserInfoModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = userInfoService.update(headerModel, userInfoModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Update user info fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public UserInfoEventBus getMenu(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            UserInfoModel userInfoModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), UserInfoModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = userInfoService.getMenu(headerModel, userInfoModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get menu fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public UserInfoEventBus changePassword(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            UserInfoModel userInfoModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), UserInfoModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = userInfoService.changePassword(headerModel, userInfoModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Change password fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public UserInfoEventBus logout(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            UserInfoModel userInfoModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), UserInfoModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = userInfoService.logout(headerModel, userInfoModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Logout fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
