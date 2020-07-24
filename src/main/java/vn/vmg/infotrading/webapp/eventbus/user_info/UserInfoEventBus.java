package vn.vmg.infotrading.webapp.eventbus.user_info;

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
import vn.vmg.infotrading.webapp.internal.user_info.service.UserInfoService;

@ProxyGen
@VertxGen
public interface UserInfoEventBus {

    @GenIgnore
    static UserInfoEventBus create(UserInfoService userInfoService, GatewayService gatewayService, Handler<AsyncResult<UserInfoEventBus>> readyHandler) {
        return new UserInfoEventBusImpl(userInfoService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.user_info.UserInfoEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.user_info.UserInfoEventBus(new UserInfoEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    UserInfoEventBus get(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    UserInfoEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    UserInfoEventBus getMenu(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    UserInfoEventBus changePassword(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    UserInfoEventBus logout(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
