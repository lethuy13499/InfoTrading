package vn.vmg.infotrading.webapp.eventbus.auth;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;
import vn.vmg.infotrading.webapp.internal.auth.service.AuthService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;

public class AuthEventBusImpl implements AuthEventBus {

    private final AuthService authService;

    private final GatewayService gatewayService;

    public AuthEventBusImpl(AuthService authService, GatewayService gatewayService, Handler<AsyncResult<AuthEventBus>> readyHandler) {
        this.authService = authService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public AuthEventBus login(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AuthModel authModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AuthModel.class, true);

            result = authService.login(headerModel, authModel);
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Login fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AuthEventBus getCode(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AuthModel authModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AuthModel.class, true);

            result = authService.getCode(headerModel, authModel);
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get code fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AuthEventBus checkCode(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AuthModel authModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AuthModel.class, true);

            result = authService.checkCode(headerModel, authModel);
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Check code fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AuthEventBus changePassword(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AuthModel authModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AuthModel.class, true);

            result = authService.changePassword(headerModel, authModel);
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Change password fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
