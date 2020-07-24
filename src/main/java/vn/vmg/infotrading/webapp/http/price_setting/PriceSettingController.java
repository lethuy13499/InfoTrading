package vn.vmg.infotrading.webapp.http.price_setting;

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
import vn.vmg.infotrading.webapp.reactivex.eventbus.price_setting.PriceSettingEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_PRICE_SETTING;

@Component
public class PriceSettingController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceSettingController.class);

    private Vertx vertx;

    private final PriceSettingEventBus priceSettingEventBus;

    public PriceSettingController(Vertx vertx) {
        this.vertx = vertx;
        this.priceSettingEventBus = vn.vmg.infotrading.webapp.eventbus.price_setting.PriceSettingEventBus.createProxy(vertx.getDelegate(), EB_PRICE_SETTING);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.get().handler(BodyHandler.create());
        router.post().handler(BodyHandler.create());
        router.post("/priceSettings").handler(this::insert);
        router.put().handler(BodyHandler.create());
        router.put("/priceSettings/:id").handler(this::update);
        router.delete().handler(BodyHandler.create());
        router.delete("/priceSettings/:id").handler(this::delete);
        return router;
    }

    public void insert(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, priceSettingEventBus.rxInsert(jsRequest), Json::encodePrettily);
    }

    public void update(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()).mergeIn(routingContext.getBodyAsJson()));

        sendResponse(routingContext, priceSettingEventBus.rxUpdate(jsRequest), Json::encodePrettily);
    }

    public void delete(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, priceSettingEventBus.rxDelete(jsRequest), Json::encodePrettily);
    }
}
