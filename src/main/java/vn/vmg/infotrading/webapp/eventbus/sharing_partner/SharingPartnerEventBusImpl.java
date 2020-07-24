package vn.vmg.infotrading.webapp.eventbus.sharing_partner;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartnerModel;
import vn.vmg.infotrading.webapp.internal.sharing_partner.service.SharingPartnerService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

public class SharingPartnerEventBusImpl implements SharingPartnerEventBus {

    private final SharingPartnerService sharingPartnerService;

    private final GatewayService gatewayService;

    public SharingPartnerEventBusImpl(SharingPartnerService sharingPartnerService, GatewayService gatewayService, Handler<AsyncResult<SharingPartnerEventBus>> readyHandler) {
        this.sharingPartnerService = sharingPartnerService;
        this.gatewayService = gatewayService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public SharingPartnerEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            SharingPartnerModel sharingPartnerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), SharingPartnerModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = sharingPartnerService.insert(sharingPartnerModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Insert sharing partner fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public SharingPartnerEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            SharingPartnerModel sharingPartnerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), SharingPartnerModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = sharingPartnerService.update(sharingPartnerModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Update sharing partner fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public SharingPartnerEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        RxResult result;
        try {
            HeaderModel headerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("headers"), HeaderModel.class, true);
            SharingPartnerModel sharingPartnerModel = JsonUtils.mapJsonObjectToClass(jsRequest.getJsonObject("body"), SharingPartnerModel.class, true);

            JsonObject jwtInfo = gatewayService.checkJwt(headerModel);
            if (jwtInfo == null) {
                result = ApiResponseUtils.buildBadRequestResponse("Jwt is invalid");
            } else {
                result = sharingPartnerService.delete(sharingPartnerModel.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = ApiResponseUtils.buildFailResponse("Delete sharing partner fail", null);
        }
        Single.just(result).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
