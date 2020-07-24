package vn.vmg.infotrading.webapp.eventbus.infomation;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.infomation.service.InformationService;

import java.io.IOException;


@ProxyGen
@VertxGen
public interface InfomationEventBus {
    @GenIgnore
    static InfomationEventBus create(InformationService infomationService, Handler<AsyncResult<InfomationEventBus>> readyHandler) {
        return new InfomationEventBusImpl(infomationService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.infomation.InfomationEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.infomation.InfomationEventBus(new InfomationEventBusVertxEBProxy(vertx, address));
    }
    @Fluent
    InfomationEventBus insertInfo(JsonObject infomations, Handler<AsyncResult<RxResult>> readyHandler);
    @Fluent
    InfomationEventBus getById(Integer id,Handler<AsyncResult<RxResult>> readyHandler);
    @Fluent
    InfomationEventBus deleteInfo(Integer id,Handler<AsyncResult<RxResult>> readyHandler);
    @Fluent
    InfomationEventBus updateInfo(JsonObject infomation,Handler<AsyncResult<RxResult>> readyHandler);
    @Fluent
    InfomationEventBus getAll(JsonObject information,Handler<AsyncResult<RxResult>> readyHandler);



}
