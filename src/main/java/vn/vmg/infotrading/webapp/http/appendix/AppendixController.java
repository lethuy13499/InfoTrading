package vn.vmg.infotrading.webapp.http.appendix;

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
import vn.vmg.infotrading.webapp.reactivex.eventbus.appendix.AppendixEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_APPENDIX_SERVICE;

@Component
public class AppendixController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppendixController.class);

    private Vertx vertx;

    private final AppendixEventBus appendixEventBus;

    public AppendixController(Vertx vertx) {
        this.vertx = vertx;
        this.appendixEventBus = vn.vmg.infotrading.webapp.eventbus.appendix.AppendixEventBus.createProxy(vertx.getDelegate(), EB_APPENDIX_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.get().handler(BodyHandler.create());
        router.get("/appendixes").handler(this::getAll);
        router.get("/appendixes/:id").handler(this::getById);
        router.post().handler(BodyHandler.create());
        router.post("/appendixes").handler(this::insert);
        router.put().handler(BodyHandler.create());
        router.put("/appendixes/:id").handler(this::update);
        router.delete().handler(BodyHandler.create());
        router.delete("/appendixes/:id").handler(this::delete);
        router.delete("/contracts/:contractId/appendixes").handler(this::deleteByContractId);
        return router;
    }

    public void getAll(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.multiMapToJsObj(routingContext.queryParams()));

        sendResponse(routingContext, appendixEventBus.rxGetAll(jsRequest), Json::encodePrettily);
    }

    public void getById(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, appendixEventBus.rxGetById(jsRequest), Json::encodePrettily);
    }

    public void insert(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, appendixEventBus.rxInsert(jsRequest), Json::encodePrettily);
    }

    public void update(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()).mergeIn(routingContext.getBodyAsJson()));

        sendResponse(routingContext, appendixEventBus.rxUpdate(jsRequest), Json::encodePrettily);
    }

    public void delete(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, appendixEventBus.rxDelete(jsRequest), Json::encodePrettily);
    }

    public void deleteByContractId(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, appendixEventBus.rxDeleteByContractId(jsRequest), Json::encodePrettily);
    }
}
