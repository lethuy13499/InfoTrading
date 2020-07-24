package vn.vmg.infotrading.webapp.eventbus.price_setting;

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
import vn.vmg.infotrading.webapp.internal.price_setting.service.PriceSettingService;

@ProxyGen
@VertxGen
public interface PriceSettingEventBus {

    @GenIgnore
    static PriceSettingEventBus create(PriceSettingService priceSettingService, GatewayService gatewayService, Handler<AsyncResult<PriceSettingEventBus>> readyHandler) {
        return new PriceSettingEventBusImpl(priceSettingService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.price_setting.PriceSettingEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.price_setting.PriceSettingEventBus(new PriceSettingEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    PriceSettingEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    PriceSettingEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    PriceSettingEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
