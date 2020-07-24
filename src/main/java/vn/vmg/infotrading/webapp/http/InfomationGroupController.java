package vn.vmg.infotrading.webapp.http;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.reactivex.eventbus.infomationGroup.InfomationGroupEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;


import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_INFOMATION_GROUPS;

@Component
public class InfomationGroupController extends RestfulApiVerticle {
    private final InfomationGroupEventBus infomationGroupEventBus;
    private final String ROUTE = "/infogroup";


    public InfomationGroupController(Vertx vertx) {
        this.infomationGroupEventBus = vn.vmg.infotrading.webapp.eventbus.infomationGroup.InfomationGroupEventBus.createProxy(vertx.getDelegate(), EB_INFOMATION_GROUPS);
    }

    public Router subRouter(Vertx vertx) {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post(ROUTE + "/create").handler(this::saveInfoGroup);
//        router.get(ROUTE).handler(BodyHandler.create());
        router.get(ROUTE + "/getByCode/:code").handler(this::searchByCode);
//        router.get(ROUTE).handler(BodyHandler.create());
        router.get(ROUTE + "getDesciption/:desciption").handler(this::searchByDescription);
        router.delete(ROUTE).handler(BodyHandler.create());
        router.delete(ROUTE + "/delete/:id").handler(this::deleteInfoGroup);
//        router.get(ROUTE).handler(BodyHandler.create());
//        router.get(ROUTE + "/:key").handler(this::searchInfoGroup);
//        router.get(ROUTE).handler(BodyHandler.create());
        router.get(ROUTE + "/getStatus/:status").handler(this::searchInfoGroupByStatus);
//        router.get(ROUTE).handler(BodyHandler.create());
        router.get(ROUTE + "/getById/:id").handler(this::getById);
//        router.get(ROUTE).handler(BodyHandler.create());
        router.get(ROUTE +"/getAll").handler(this::getAll);
        router.put(ROUTE).handler(BodyHandler.create());


        return router;
    }

    private void getAll(RoutingContext routingContext) {
        JsonObject params = JsonUtils.multiMapToJsObjLowerKey(routingContext.queryParams());
        try {
            sendResponse(routingContext, infomationGroupEventBus.rxGetAll(params), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
        }
    }

    private void getById(RoutingContext routingContext) {
        Integer id = Integer.valueOf(routingContext.request().getParam("id"));
        try {
            sendResponse(routingContext,
                    infomationGroupEventBus.rxGetById(id), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    private void searchInfoGroupByStatus(RoutingContext routingContext) {
        Integer status = Integer.valueOf(routingContext.request().getParam("status"));
        try{
            sendResponse(routingContext,infomationGroupEventBus.rxSearchByStatus(status),Json::encodePrettily);
        }catch (DecodeException ex){
            badRequest(routingContext,ex);
        }
    }

    private void searchInfoGroup(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,
                    infomationGroupEventBus.rxSearchInfoGroup(params),
                    Json::encodePrettily);

        } catch (DecodeException ex) {

            badRequest(routingContext, ex);
        }
    }

    private void deleteInfoGroup(RoutingContext routingContext) {
        Integer id = Integer.valueOf(routingContext.request().getParam("id"));
        try {
            sendResponse(routingContext,
                    infomationGroupEventBus.rxDeleteInfoGroup(id), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }

    }

    private void searchByDescription(RoutingContext routingContext) {
        String desciption = routingContext.request().getParam("desciption");
        try {
            sendResponse(routingContext,
                    infomationGroupEventBus.rxSearchByDescription(desciption), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    public void saveInfoGroup(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,
                    infomationGroupEventBus.rxInsertInfoGroup(params),
                    Json::encodePrettily);

        } catch (DecodeException ex) {

            badRequest(routingContext, ex);
        }

    }

    public void searchByCode(RoutingContext routingContext) {
        String code = routingContext.request().getParam("code");
        try {
            sendResponse(routingContext, infomationGroupEventBus.rxSearchByCode(code),
                    Json::encodePrettily
            );
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
        }
    }

}
