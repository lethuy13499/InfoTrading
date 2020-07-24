package vn.vmg.infotrading.webapp.http;


import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.reactivex.eventbus.infomation.InfomationEventBus;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_INFOMATION_ADDRESS;


@Component
public class InfomationController extends RestfulApiVerticle {
    private final InfomationEventBus infomationEventBus;
    private final String ROUTE = "/infomation";

    public InfomationController(Vertx vertx) {
        this.infomationEventBus = vn.vmg.infotrading.webapp.eventbus.infomation.InfomationEventBus.createProxy(vertx.getDelegate(), EB_INFOMATION_ADDRESS);
    }

    public Router subRouter(Vertx vertx) {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post(ROUTE + "/create").handler(this::insertInfo);
        router.delete(ROUTE).handler(BodyHandler.create());
        router.delete(ROUTE + "/:id").handler(this::deleteInfo);
        router.get().handler(BodyHandler.create());
        router.get(ROUTE + "/getById/:id").handler(this::getInfoById);
        router.post().handler(BodyHandler.create());
        router.get(ROUTE + "/getAll").handler(this::getAll);
        router.get().handler(BodyHandler.create());


        return router;
    }

    private void getAll(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        sendResponse(routingContext,
                infomationEventBus.rxGetAll(jsRequest), Json::encodePrettily);

    }

    private void deleteInfo(RoutingContext routingContext) {
        Integer id = Integer.valueOf(routingContext.request().getParam("id"));
        try {
            sendResponse(routingContext,
                    infomationEventBus.rxDeleteInfo(id), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    private void getInfoById(RoutingContext routingContext) {
        Integer id = Integer.valueOf(routingContext.request().getParam("id"));
        try {
            sendResponse(routingContext,
                    infomationEventBus.rxGetById(id), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }


    }

    private void insertInfo(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,
                    infomationEventBus.rxInsertInfo(params),
                    Json::encodePrettily);

        } catch (DecodeException ex) {

            badRequest(routingContext, ex);
        }

    }
}
