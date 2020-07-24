package vn.vmg.infotrading.webapp.eventbus.infomationGroup;

import io.vertx.codegen.annotations.Fluent;
import io.vertx.codegen.annotations.GenIgnore;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.service.InfomationGroupService;

import java.io.IOException;


@ProxyGen
@VertxGen
public interface InfomationGroupEventBus {
    @GenIgnore
    static InfomationGroupEventBus create(InfomationGroupService infomationGroupService, Handler<AsyncResult<InfomationGroupEventBus>> readyHandler) {
        return new InfomationGroupEventBusImpl(infomationGroupService, readyHandler);
    }

    @GenIgnore
    static vn.vmg.infotrading.webapp.reactivex.eventbus.infomationGroup.InfomationGroupEventBus createProxy(Vertx vertx, String address) {
        return new vn.vmg.infotrading.webapp.reactivex.eventbus.infomationGroup.InfomationGroupEventBus(new InfomationGroupEventBusVertxEBProxy(vertx, address));
    }

    @Fluent
    InfomationGroupEventBus insertInfoGroup(JsonObject infoGroup, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus searchByCode(String code, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus updateInfoGroup(JsonObject infoGroup, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus searchByDescription(String description, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus searchByStatus(Integer status, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus getById(Integer id, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus deleteInfoGroup(Integer id, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus searchInfoGroup(JsonObject infoGroups, Handler<AsyncResult<RxResult>> readyHandler);

    @Fluent
    InfomationGroupEventBus getAll(JsonObject jsonObject,Handler<AsyncResult<RxResult>> readyHandler);
}
