package vn.vmg.infotrading.webapp;

import io.vertx.reactivex.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import vn.vmg.infotrading.webapp.eventbus.RxEventBusVerticle;
import vn.vmg.infotrading.webapp.http.HttpServerVerticle;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
public class WebappApplication {

    @Autowired
    private HttpServerVerticle httpServerVerticle;

    @Autowired
    private RxEventBusVerticle rxEventBusVerticle;

    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }

    @PostConstruct
    public void postSetting() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("Asia/Ho_Chi_Minh", ZoneId.SHORT_IDS)));
        final Vertx vertx = vertx();
        vertx.rxDeployVerticle(rxEventBusVerticle)
                .flatMap(id -> vertx.rxDeployVerticle(httpServerVerticle))
                .subscribe();
    }

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
