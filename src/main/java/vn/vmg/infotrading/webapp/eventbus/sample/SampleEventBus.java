package vn.vmg.infotrading.webapp.eventbus.sample;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.sample.service.SampleService;

@ProxyGen
@VertxGen
public interface SampleEventBus {

    @GenIgnore
    static SampleEventBus create(SampleService sampleService, Handler<AsyncResult<SampleEventBus>> readyHandler) {
        return new SampleEventBusImpl(sampleService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.sample.SampleEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.sample.SampleEventBus(new SampleEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    SampleEventBus register(JsonObject body, Handler<AsyncResult<RxResult>> readyHandler);

}
