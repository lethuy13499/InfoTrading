package vn.vmg.infotrading.webapp.eventbus.auth;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.auth.service.AuthService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;

@ProxyGen
@VertxGen
public interface AuthEventBus {

    @GenIgnore
    static AuthEventBus create(AuthService authService, GatewayService gatewayService, Handler<AsyncResult<AuthEventBus>> readyHandler) {
        return new AuthEventBusImpl(authService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.auth.AuthEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.auth.AuthEventBus(new AuthEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    AuthEventBus login(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AuthEventBus getCode(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AuthEventBus checkCode(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    AuthEventBus changePassword(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
