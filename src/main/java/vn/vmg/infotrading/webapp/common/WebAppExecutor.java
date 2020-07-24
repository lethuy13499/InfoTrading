package vn.vmg.infotrading.webapp.common;

import io.vertx.reactivex.core.Vertx;

public final class WebAppExecutor {
    private static WebAppExecutor INSTANCE;
    private Vertx vertx;

    private WebAppExecutor() {
    }

    public static WebAppExecutor initialize(Vertx vertx) {
        if (INSTANCE == null) {
            INSTANCE = new WebAppExecutor();
        }
        INSTANCE.vertx = vertx;
        return INSTANCE;
    }

    public static WebAppExecutor getInstance() {
        if (INSTANCE == null) {
            throw new NullPointerException();
        }
        return INSTANCE;
    }
}
