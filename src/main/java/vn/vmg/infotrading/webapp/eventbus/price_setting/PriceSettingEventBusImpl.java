package vn.vmg.infotrading.webapp.eventbus.price_setting;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSettingModel;
import vn.vmg.infotrading.webapp.internal.price_setting.service.PriceSettingService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

public class PriceSettingEventBusImpl implements PriceSettingEventBus {

    private final PriceSettingService priceSettingService;

    private final GatewayService gatewayService;

    public PriceSettingEventBusImpl(PriceSettingService priceSettingService, GatewayService gatewayService, Handler<AsyncResult<PriceSettingEventBus>> readyHandler) {
        this.priceSettingService = priceSettingService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public PriceSettingEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            PriceSettingModel priceSettingModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), PriceSettingModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = priceSettingService.insert(priceSettingModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Insert price setting fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public PriceSettingEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            PriceSettingModel priceSettingModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), PriceSettingModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = priceSettingService.update(priceSettingModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Update price setting fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public PriceSettingEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            PriceSettingModel priceSettingModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), PriceSettingModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = priceSettingService.delete(priceSettingModel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete price setting fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
