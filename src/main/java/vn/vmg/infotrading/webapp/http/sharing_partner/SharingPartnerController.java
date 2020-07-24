package vn.vmg.infotrading.webapp.http.sharing_partner;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.http.RestfulApiVerticle;
import vn.vmg.infotrading.webapp.reactivex.eventbus.sharing_partner.SharingPartnerEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_SHARING_PARTNER_SERVICE;

@Component
public class SharingPartnerController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(SharingPartnerController.class);

    private Vertx vertx;

    private final SharingPartnerEventBus sharingPartnerEventBus;

    public SharingPartnerController(Vertx vertx) {
        this.vertx = vertx;
        this.sharingPartnerEventBus = vn.vmg.infotrading.webapp.eventbus.sharing_partner.SharingPartnerEventBus.createProxy(vertx.getDelegate(), EB_SHARING_PARTNER_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post("/sharingPartners").handler(this::insert);
        router.put().handler(BodyHandler.create());
        router.put("/sharingPartners/:id").handler(this::update);
        router.delete().handler(BodyHandler.create());
        router.delete("/sharingPartners/:id").handler(this::delete);
        return router;
    }

    public void insert(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, sharingPartnerEventBus.rxInsert(jsRequest), Json::encodePrettily);
    }

    public void update(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()).mergeIn(routingContext.getBodyAsJson()));

        sendResponse(routingContext, sharingPartnerEventBus.rxUpdate(jsRequest), Json::encodePrettily);
    }

    public void delete(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, sharingPartnerEventBus.rxDelete(jsRequest), Json::encodePrettily);
    }
}
