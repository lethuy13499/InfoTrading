package vn.vmg.infotrading.webapp.eventbus.common;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.common.domain.CommonModel;
import vn.vmg.infotrading.webapp.internal.common.service.CommonService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.parameter.domain.ParameterModel;
import vn.vmg.infotrading.webapp.internal.theme.domain.ThemeModel;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;

public class CommonEventBusImpl implements CommonEventBus {

    private final CommonService commonService;

    private final GatewayService gatewayService;

    public CommonEventBusImpl(CommonService commonService, GatewayService gatewayService, Handler<AsyncResult<CommonEventBus>> readyHandler) {
        this.commonService = commonService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public CommonEventBus getParameters(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            ParameterModel parameterModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), ParameterModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = commonService.getParameters(headerModel, parameterModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get parameters fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CommonEventBus getThemeById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            ThemeModel themeModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), ThemeModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = commonService.getThemeById(headerModel, themeModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get theme by id fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public CommonEventBus getPasswordProtectedConfig(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            CommonModel commonModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), CommonModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = commonService.getPasswordProtectedConfig(headerModel, commonModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get password protected config fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
