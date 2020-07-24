package vn.vmg.infotrading.webapp.eventbus.appendix;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.appendix.service.AppendixService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;

@ProxyGen
@VertxGen
public interface AppendixEventBus {

    @GenIgnore
    static AppendixEventBus create(AppendixService appendixService, GatewayService gatewayService, Handler<AsyncResult<AppendixEventBus>> readyHandler) {
        return new AppendixEventBusImpl(appendixService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.appendix.AppendixEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.appendix.AppendixEventBus(new AppendixEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    AppendixEventBus getAll(JsonObject jsRequest, Handler<AsyncResult<JsonObject>> readyHandler);

    @Fluent
    AppendixEventBus getById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AppendixEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AppendixEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AppendixEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AppendixEventBus deleteByContractId(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
