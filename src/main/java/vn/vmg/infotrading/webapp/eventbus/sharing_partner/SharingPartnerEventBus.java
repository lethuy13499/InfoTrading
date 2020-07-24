package vn.vmg.infotrading.webapp.eventbus.sharing_partner;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;
import vn.vmg.infotrading.webapp.internal.sharing_partner.service.SharingPartnerService;

@ProxyGen
@VertxGen
public interface SharingPartnerEventBus {

    @GenIgnore
    static SharingPartnerEventBus create(SharingPartnerService sharingPartnerService, GatewayService gatewayService, Handler<AsyncResult<SharingPartnerEventBus>> readyHandler) {
        return new SharingPartnerEventBusImpl(sharingPartnerService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.sharing_partner.SharingPartnerEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.sharing_partner.SharingPartnerEventBus(new SharingPartnerEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    SharingPartnerEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    SharingPartnerEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    SharingPartnerEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
