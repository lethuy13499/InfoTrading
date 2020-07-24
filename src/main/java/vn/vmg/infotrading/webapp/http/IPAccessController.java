package vn.vmg.infotrading.webapp.http;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;

import vn.vmg.infotrading.webapp.http.RestfulApiVerticle;
import vn.vmg.infotrading.webapp.reactivex.eventbus.ipAccess.IPAccessEventBus;


import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_IP_ACCESS;

@Component
public class IPAccessController extends RestfulApiVerticle {
    private final IPAccessEventBus ipAccessEventBus;
    private final String ROUTE = "/ipAccess";

    public IPAccessController(Vertx vertx) {
        this.ipAccessEventBus = vn.vmg.infotrading.webapp.eventbus.ipAccess.IPAccessEventBus.createProxy(vertx.getDelegate(), EB_IP_ACCESS);
    }

    public Router subRouter(Vertx vertx) {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post(ROUTE + "/create").handler(this::saveIpAccess);
        router.get(ROUTE).handler(BodyHandler.create());
        router.post(ROUTE + "/:ip").handler(this::getIpAccessByIp);
        router.post().handler(BodyHandler.create());
        //   router.get(ROUTE+"download").handler(this::)

        return router;
    }

    public void saveIpAccess(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,
                    ipAccessEventBus.rxInsertIpAccess(params),
                    Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
        }
    }

    public void getIpAccessByIp(RoutingContext routingContext) {
        String ip = routingContext.request().getParam("ip");
        try {
            sendResponse(routingContext,
                    ipAccessEventBus.rxGetIpAccessByIp(ip), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    public void downloafFile(RoutingContext routingContext) {

    }
}
