package vn.vmg.infotrading.webapp.eventbus.common;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.common.service.CommonService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;

@ProxyGen
@VertxGen
public interface CommonEventBus {

    @GenIgnore
    static CommonEventBus create(CommonService userService, GatewayService gatewayService, Handler<AsyncResult<CommonEventBus>> readyHandler) {
        return new CommonEventBusImpl(userService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.common.CommonEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.common.CommonEventBus(new CommonEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    CommonEventBus getParameters(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    CommonEventBus getThemeById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    CommonEventBus getPasswordProtectedConfig(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
