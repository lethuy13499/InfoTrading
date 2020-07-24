package vn.vmg.infotrading.webapp.http.category_data;


import io.vertx.core.json.DecodeException;
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
import vn.vmg.infotrading.webapp.reactivex.eventbus.category_data.CategoryDataEventBus;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_CATEGORY_DATA;


@Component
public class CategoryDataController extends RestfulApiVerticle {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDataController.class);

    private Vertx vertx;

    private final CategoryDataEventBus categoryDataEventBus;

    public CategoryDataController(Vertx vertx) {
        this.vertx = vertx;
        this.categoryDataEventBus = vn.vmg.infotrading.webapp.eventbus.category_data.CategoryDataEventBus.createProxy(vertx.getDelegate(), EB_CATEGORY_DATA);
    }

    public Router subRouter() {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.get().handler(BodyHandler.create());
        router.get("/categoryData").handler(this::getAll);
        router.get("/categoryData/:id").handler(this::getById);
        router.post().handler(BodyHandler.create());
        router.post("/categoryData").handler(this::insert);
        router.put().handler(BodyHandler.create());
        router.put("/categoryData/:id").handler(this::update);
        router.delete().handler(BodyHandler.create());
        router.delete("/categoryData/:id").handler(this::delete);
        return router;
    }

    private void getAll(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.multiMapToJsObj(routingContext.queryParams()));
        try {
            sendResponse(routingContext,
                    categoryDataEventBus.rxGetAll(jsRequest), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    private void getById(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));
        try {
            sendResponse(routingContext,
                categoryDataEventBus.rxGetById(jsRequest), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }

    public void insert(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", routingContext.getBodyAsJson());

        sendResponse(routingContext, categoryDataEventBus.rxInsert(jsRequest), Json::encodePrettily);
    }

    public void update(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()).mergeIn(routingContext.getBodyAsJson()));

        sendResponse(routingContext, categoryDataEventBus.rxUpdate(jsRequest), Json::encodePrettily);
    }

    public void delete(RoutingContext routingContext) {
        JsonObject jsRequest = new JsonObject();
        jsRequest.put("headers", JsonUtils.multiMapToJsObjLowerKey(routingContext.request().headers()));
        jsRequest.put("body", JsonUtils.mapToJsObj(routingContext.pathParams()));

        sendResponse(routingContext, categoryDataEventBus.rxDelete(jsRequest), Json::encodePrettily);
    }

}
