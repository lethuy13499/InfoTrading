package vn.vmg.infotrading.webapp.http.appendix_file;

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
import vn.vmg.infotrading.webapp.reactivex.eventbus.appendix_file.AppendixFileEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_APPENDIX_FILE_SERVICE;

@Component
public class AppendixFileController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppendixFileController.class);

    private Vertx vertx;

    private final AppendixFileEventBus appendixFileEventBus;

    public AppendixFileController(Vertx vertx) {
        this.vertx = vertx;
        this.appendixFileEventBus = vn.vmg.infotrading.webapp.eventbus.appendix_file.AppendixFileEventBus.createProxy(vertx.getDelegate(), EB_APPENDIX_FILE_SERVICE);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post("/appendixFiles").handler(this::insert);
        router.delete().handler(BodyHandler.create());
        router.delete("/appendixFiles/:id").handler(this::delete);
        return router;
    }

    public void insert(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, appendixFileEventBus.rxInsert(jsRequest), Json::encodePrettily);
    }

    public void delete(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, appendixFileEventBus.rxDelete(jsRequest), Json::encodePrettily);
    }
}
