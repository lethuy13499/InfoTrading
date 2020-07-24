package vn.vmg.infotrading.webapp.eventbus.appendix_file;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;
import vn.vmg.infotrading.webapp.internal.appendix_file.service.AppendixFileService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

public class AppendixFileEventBusImpl implements AppendixFileEventBus {

    private final AppendixFileService appendixFileService;

    private final GatewayService gatewayService;

    public AppendixFileEventBusImpl(AppendixFileService appendixFileService, GatewayService gatewayService, Handler<AsyncResult<AppendixFileEventBus>> readyHandler) {
        this.appendixFileService = appendixFileService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public AppendixFileEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixFileModel appendixFileModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixFileModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixFileService.insert(appendixFileModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Insert appendix file fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public AppendixFileEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            AppendixFileModel appendixFileModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), AppendixFileModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = appendixFileService.delete(appendixFileModel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete appendix file fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
