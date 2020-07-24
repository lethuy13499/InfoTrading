package vn.vmg.infotrading.webapp.http;

import io.vertx.core.json.DecodeException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import io.vertx.reactivex.ext.web.handler.BodyHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.reactivex.eventbus.partner.PartnerEventBus;

import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_PARTNER_ADDRESS;

@Component
@Transactional
public class PartnerController extends RestfulApiVerticle {
    private final PartnerEventBus partnerEventBus;
    private final String ROUTE = "/partners";
    public PartnerController(Vertx vertx) {
        this.partnerEventBus = vn.vmg.infotrading.webapp.eventbus.partner.PartnerEventBus.createProxy(vertx.getDelegate(), EB_PARTNER_ADDRESS);

    }

    public Router subRouter(Vertx vertx) {
        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.post().handler(BodyHandler.create());
        router.post(ROUTE + "/create").handler(this::savePartner);
        router.get(ROUTE + "/:id").handler(this::getPartnerByParentId);
        router.put(ROUTE).handler(BodyHandler.create());
        router.put(ROUTE + "/update/:id").handler(this::update);
        router.get(ROUTE + "/getAll").handler(this::getAll);


        return router;
    }

    private void getAll(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,partnerEventBus.rxUpdate(params),Json::encodePrettily);
        }catch (DecodeException ex){
            badRequest(routingContext,ex);
        }
    }

    private void update(RoutingContext routingContext) {
        try {
            sendResponse(routingContext, partnerEventBus.rxUpdate(routingContext.getBodyAsJson()),
                    Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
        }
    }

    public void savePartner(RoutingContext routingContext) {
        JsonObject params = routingContext.getBodyAsJson();
        try {
            sendResponse(routingContext,
                    partnerEventBus.rxInsertPartner(params),
                    Json::encodePrettily);

        } catch (DecodeException ex) {

            badRequest(routingContext, ex);
        }

    }

    public void getPartnerByParentId(RoutingContext routingContext) {
        Integer partnerParent = Integer.valueOf(routingContext.request().getParam("id"));
        try {
            sendResponse(routingContext,
                    partnerEventBus.rxGetPartnerByParent(partnerParent), Json::encodePrettily);
        } catch (DecodeException ex) {
            badRequest(routingContext, ex);
            ex.printStackTrace();
        }
    }
}
