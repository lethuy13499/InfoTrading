package vn.vmg.infotrading.webapp.http;

import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.handler.CorsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.eventbus.RxEventBusVerticle;
import vn.vmg.infotrading.webapp.http.appendix.AppendixController;
import vn.vmg.infotrading.webapp.http.appendix_file.AppendixFileController;
import vn.vmg.infotrading.webapp.http.auth.AuthController;
import vn.vmg.infotrading.webapp.http.category_data.CategoryDataController;
import vn.vmg.infotrading.webapp.http.common.CommonController;
import vn.vmg.infotrading.webapp.http.price_setting.PriceSettingController;
import vn.vmg.infotrading.webapp.http.sharing_partner.SharingPartnerController;
import vn.vmg.infotrading.webapp.http.user_info.UserInfoController;

import java.util.HashSet;
import java.util.Set;

@Component
public class HttpServerVerticle extends AbstractVerticle  {
    private static final Logger LOGGER = LoggerFactory.getLogger(RxEventBusVerticle.class);

    @Autowired
    private PartnerController partnerController;
    @Autowired
    private IPAccessController ipAccessController;
    @Autowired
    private InfomationController infomationController;
    @Autowired
    private InfomationGroupController infomationGroupController;

    @Autowired
    private AuthController authController;

    @Autowired
    private CommonController commonController;

    @Autowired
    private UserInfoController userInfoController;

    @Autowired
    private AppendixController appendixController;

    @Autowired
    private AppendixFileController appendixFileController;

    @Autowired
    private SharingPartnerController sharingPartnerController;

    @Autowired
    private CategoryDataController categoryDataController;

    @Autowired
    private PriceSettingController priceSettingController;
    @Override
    public void start(Promise<Void> promise) throws Exception {
        super.start();

        LOGGER.info("init HttpServerVerticle... ");

        Router router = Router.router(vertx);
        enableCorsSupport(router);
        router.mountSubRouter("/api", partnerController.subRouter(vertx));
        router.mountSubRouter("/api", ipAccessController.subRouter(vertx));
        router.mountSubRouter("/api", infomationController.subRouter(vertx));
        router.mountSubRouter("/api", infomationGroupController.subRouter(vertx));
//        router.mountSubRouter("/api", authController.subRouter(vertx));
//        router.mountSubRouter("/api", commonController.subRouter(vertx));
//        router.mountSubRouter("/api", userInfoController.subRouter(vertx));
//        router.mountSubRouter("/api", appendixController.subRouter(vertx));
//        router.mountSubRouter("/api", appendixFileController.subRouter(vertx));
//        router.mountSubRouter("/api", sharingPartnerController.subRouter(vertx));
//        router.mountSubRouter("/api", categoryDataController.subRouter(vertx));
//        router.mountSubRouter("/api", priceSettingController.subRouter(vertx));
        router.mountSubRouter("/api", authController.subRouter());
        router.mountSubRouter("/api", commonController.subRouter());
        router.mountSubRouter("/api", userInfoController.subRouter());
        router.mountSubRouter("/api", appendixController.subRouter());
        router.mountSubRouter("/api", appendixFileController.subRouter());
        router.mountSubRouter("/api", sharingPartnerController.subRouter());
        router.mountSubRouter("/api", categoryDataController.subRouter());
        router.mountSubRouter("/api", priceSettingController.subRouter());

        vertx.createHttpServer()
                .requestHandler(router)
                .rxListen(8082)
                .subscribe(server -> {
                    LOGGER.info("Server stated at port 8082");
                    promise.complete();
                }, promise::fail);
    }
    /**
     * Enable CORS support for web router.
     *
     * @param router router instance
     */
    protected void enableCorsSupport(Router router) {
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        // CORS support
        router.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.PUT)
        );
    }

}
