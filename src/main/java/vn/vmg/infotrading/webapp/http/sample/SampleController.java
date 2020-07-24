package vn.vmg.infotrading.webapp.http.sample;

import io.vertx.core.json.Json;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.http.RestfulApiVerticle;
import vn.vmg.infotrading.webapp.reactivex.eventbus.sample.SampleEventBus;


import static vn.vmg.infotrading.webapp.eventbus.EventBusConstant.EB_ADDRESS_SAMPLE;

//@Component
public class SampleController extends RestfulApiVerticle {
    private final SampleEventBus sampleEventBus;

    public SampleController(Vertx vertx) {
        sampleEventBus = vn.vmg.infotrading.webapp.eventbus.sample.SampleEventBus.createProxy(vertx.getDelegate(), EB_ADDRESS_SAMPLE);
    }

    public void testAPI(RoutingContext routingContext) {
        sendResponse(routingContext, sampleEventBus.rxRegister(routingContext.getBodyAsJson()), Json::encodePrettily);
    }
}
