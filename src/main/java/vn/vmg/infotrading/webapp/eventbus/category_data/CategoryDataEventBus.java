package vn.vmg.infotrading.webapp.eventbus.category_data;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.category_data.service.CategoryDataService;
import vn.vmg.infotrading.webapp.internal.gateway.service.GatewayService;


@ProxyGen
@VertxGen
public interface CategoryDataEventBus {
    @GenIgnore
    static vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus create(CategoryDataService categoryDataService, GatewayService gatewayService, Handler<AsyncResult<CategoryDataEventBus>> readyHandler) {
        return new CategoryDataEventBusImpl(categoryDataService, gatewayService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.category_data.CategoryDataEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.category_data.CategoryDataEventBus(new CategoryDataEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus getAll(JsonObject jsRequest, Handler<AsyncResult<JsonObject>> readyHandler);

    @Fluent
    vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus getById(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus insert(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus update(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus delete(JsonObject jsRequest, Handler<AsyncResult<RxResult>> readyHandler);
}
