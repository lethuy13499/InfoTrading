package vn.vmg.infotrading.webapp.eventbus.appendix;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.appendix.domain.AppendixModel;
import vn.vmg.infotrading.webapp.internal.appendix.service.AppendixService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;

public class AppendixEventBusImpl implements AppendixEventBus {

    private final AppendixService appendixService;

    private final GatewayService gatewayService;

    public AppendixEventBusImpl(AppendixService appendixService, GatewayService gatewayService, Handler<AsyncResult<AppendixEventBus>> readyHandler) {
        this.appendixService = appendixService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public AppendixEventBus getAll(JsonObject jsRequest, Handler<AsyncResult<JsonObject>> readyHandler) {
        JsonObject result = new JsonObject();
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result.put("status", false);
                result.put("message", "Jwt is invalid");
            } else {
                result = appendixService.getAll(appendixModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("status", false);
            result.put("message", "Get appendixes fail");
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixEventBus getById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixService.getById(appendixModel.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Get appendix by id fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixService.insert(appendixModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Insert appendix fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixService.update(appendixModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Update appendix fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixService.delete(appendixModel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete appendix fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixEventBus deleteByContractId(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixModel appendixModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixService.deleteByContractId(appendixModel.getContractId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete appendix by contract id fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
