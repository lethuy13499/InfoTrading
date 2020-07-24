package vn.vmg.infotrading.webapp.eventbus.sample;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.sample.domain.RegisterInfo;
import vn.vmg.infotrading.webapp.internal.sample.service.SampleService;

public class SampleEventBusImpl implements SampleEventBus {
    private final SampleService sampleService;

    public SampleEventBusImpl(SampleService sampleService, Handler<AsyncResult<SampleEventBus>> readyHandler) {
        this.sampleService = sampleService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public SampleEventBus register(JsonObject body, Handler<AsyncResult<RxResult>> readyHandler) {
        RegisterInfo info = body.mapTo(RegisterInfo.class);
        InternalResult internalResult = sampleService.register(info);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
            rxResult.setContent(info);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("Đăng ký không thành công!");
            rxResult.setContent(internalResult.getErrors());
        }

        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
}
