package vn.vmg.infotrading.webapp.eventbus.appendix_file;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.appendix_file.service.AppendixFileService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;

@ProxyGen
@VertxGen
public interface AppendixFileEventBus {

    @GenIgnore
    static AppendixFileEventBus create(AppendixFileService appendixFileService, GatewayService gatewayService, Handler<AsyncResult<AppendixFileEventBus>> readyHandler) {
        return new AppendixFileEventBusImpl(appendixFileService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.appendix_file.AppendixFileEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.appendix_file.AppendixFileEventBus(new AppendixFileEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    AppendixFileEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AppendixFileEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
