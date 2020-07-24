package vn.vmg.infotrading.webapp.http.auth;

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
import vn.vmg.infotrading.webapp.reactivex.eventbus.auth.AuthEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_AUTH_SERVICE;

@Component
public class AuthController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private Vertx vertx;

    private final AuthEventBus authEventBus;

    public AuthController(Vertx vertx) {
        this.vertx = vertx;
        this.authEventBus = vn.vmg.infotrading.webapp.eventbus.auth.AuthEventBus.createProxy(vertx.getDelegate(), EB_AUTH_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post("/auth/login").handler(this::login);
        router.post("/auth/change-password").handler(this::changePassword);
        router.post("/auth/get-code").handler(this::getCode);
        router.post("/auth/check-code").handler(this::checkCode);
        return router;
    }

    public void login(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, authEventBus.rxLogin(jsRequest), Json::encodePrettily);
    }

    public void getCode(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, authEventBus.rxGetCode(jsRequest), Json::encodePrettily);
    }

    public void checkCode(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, authEventBus.rxCheckCode(jsRequest), Json::encodePrettily);
    }

    public void changePassword(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, authEventBus.rxChangePassword(jsRequest), Json::encodePrettily);
    }
}
