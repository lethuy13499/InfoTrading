package vn.vmg.infotrading.webapp.eventbus.partner;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.partner.service.PartnerService;

@ProxyGen
@VertxGen
public interface PartnerEventBus {
    @GenIgnore
    static PartnerEventBus create(PartnerService partnerService, Handler<AsyncResult<PartnerEventBus>> readyHandler) {
        return new PartnerEventBusImpl(partnerService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.partner.PartnerEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.partner.PartnerEventBus(new PartnerEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    PartnerEventBus insertPartner(JsonObject partnerRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    PartnerEventBus getPartnerByParent(Integer partnerId, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    PartnerEventBus update(JsonObject partnerRequest, Handler<AsyncResult<RxResult>> readyHandler);

//    @Fluent
//    PartnerEventBus delete(Integer id, Handler<AsyncResult<RxResult>> readyHandler);
    @Fluent
    PartnerEventBus getAllPartner(JsonObject partnerRequest,Handler<AsyncResult<RxResult>> readyHandler);

}
