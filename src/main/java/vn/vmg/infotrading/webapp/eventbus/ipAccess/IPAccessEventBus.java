package vn.vmg.infotrading.webapp.eventbus.ipAccess;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.ipAccess.service.IPAccessService;

@ProxyGen
@VertxGen
public interface IPAccessEventBus {
    @GenIgnore
    static IPAccessEventBus create(IPAccessService ipAccessService, Handler<AsyncResult<IPAccessEventBus>> readyHandler) {
        return new IPAccessEventBusImpl(ipAccessService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.ipAccess.IPAccessEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.ipAccess.IPAccessEventBus(new IPAccessEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    IPAccessEventBus insertIpAccess(JsonObject ipAccess, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    IPAccessEventBus getIpAccessByIp(String ip, Handler<AsyncResult<RxResult>> resultHandler);

    @Fluent
    IPAccessEventBus updateIPAccess(JsonObject ipAccess, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    IPAccessEventBus delete(Integer id, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    IPAccessEventBus findById(Integer id, Handler<AsyncResult<RxResult>> readyHandler);

}
