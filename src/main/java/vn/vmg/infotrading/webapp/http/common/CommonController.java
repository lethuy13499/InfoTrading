package vn.vmg.infotrading.webapp.http.common;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.http.RestfulApiVerticle;
import vn.vmg.infotrading.webapp.reactivex.eventbus.common.CommonEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_COMMON_SERVICE;

@Component
public class CommonController extends RestfulApiVerticle {

    private Vertx vertx;

    private final CommonEventBus commonEventBus;

    public CommonController(Vertx vertx) {
        this.vertx = vertx;
        this.commonEventBus = vn.vmg.infotrading.webapp.eventbus.common.CommonEventBus.createProxy(vertx.getDelegate(), EB_COMMON_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.get().handler(BodyHandler.create());
        router.get("/common/parameters").handler(this::getParameters);
        router.get("/common/themes/:id").handler(this::getThemeById);
        router.get("/common/password-protected-config").handler(this::getPasswordProtectedConfig);
        return router;
    }

    public void getParameters(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.multiMapToJsObj(routingContext.queryParams()));

        sendResponse(routingContext, commonEventBus.rxGetParameters(jsRequest), Json::encodePrettily);
    }

    public void getThemeById(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, commonEventBus.rxGetThemeById(jsRequest), Json::encodePrettily);
    }

    public void getPasswordProtectedConfig(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, commonEventBus.rxGetPasswordProtectedConfig(jsRequest), Json::encodePrettily);
    }
}
