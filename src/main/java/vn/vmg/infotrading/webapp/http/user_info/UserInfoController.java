package vn.vmg.infotrading.webapp.http.user_info;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.http.RestfulApiVerticle;
import vn.vmg.infotrading.webapp.reactivex.eventbus.user_info.UserInfoEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_USER_INFO_SERVICE;

@Component
public class UserInfoController extends RestfulApiVerticle {

    private Vertx vertx;

    private final UserInfoEventBus userInfoEventBus;

    public UserInfoController(Vertx vertx) {
        this.vertx = vertx;
        this.userInfoEventBus = vn.vmg.infotrading.webapp.eventbus.user_info.UserInfoEventBus.createProxy(vertx.getDelegate(), EB_USER_INFO_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.get().handler(BodyHandler.create());
        router.get("/user-info").handler(this::get);
        router.get("/user-info/get-menu").handler(this::getMenu);
        router.post().handler(BodyHandler.create());
        router.post("/user-info/change-password").handler(this::changePassword);
        router.post("/user-info/logout").handler(this::logout);
        router.put().handler(BodyHandler.create());
        router.put("/user-info").handler(this::update);
        return router;
    }

    public void get(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, userInfoEventBus.rxGet(jsRequest), Json::encodePrettily);
    }

    public void update(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, userInfoEventBus.rxUpdate(jsRequest), Json::encodePrettily);
    }

    public void getMenu(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, userInfoEventBus.rxGetMenu(jsRequest), Json::encodePrettily);
    }

    public void changePassword(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, userInfoEventBus.rxChangePassword(jsRequest), Json::encodePrettily);
    }

    public void logout(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, userInfoEventBus.rxLogout(jsRequest), Json::encodePrettily);
    }
}
