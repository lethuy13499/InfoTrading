package vn.vmg.infotrading.webapp.eventbus.infomation;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.vmg.infotrading.webapp.eventbus.RxResult;

import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.infomation.service.InformationService;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.io.IOException;
import java.util.List;

public class InfomationEventBusImpl implements InfomationEventBus {
    private final InformationService infomationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(InfomationEventBusImpl.class);

    public InfomationEventBusImpl(InformationService infomationService, Handler<AsyncResult<InfomationEventBus>> readyHandler) {
        this.infomationService = infomationService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public InfomationEventBus insertInfo(JsonObject infomation, Handler<AsyncResult<RxResult>> readyHandler) {
        Information information = infomation.mapTo(Information.class);
        InternalResult internalResult = infomationService.insertInfo(information);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setContent(internalResult);
            rxResult.setStatus(true);
            rxResult.setMessage("thành công");
        } else {
            rxResult.setMessage("error");
            rxResult.setStatus(false);
            rxResult.setContent(internalResult.hasErrors());
        }

        return this;
    }

    @Override
    public InfomationEventBus getById(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        List<Information> internalResult = infomationService.getById(id);
        RxResult rxResult = new RxResult();
        if (internalResult.size() > 0) {
            rxResult.setMessage("thành công");
            rxResult.setStatus(true);
            rxResult.setContent(internalResult);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("error");
            rxResult.setContent(internalResult);
        }
        return this;
    }

    @Override
    public InfomationEventBus deleteInfo(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        InternalResult internalResult = infomationService.deleteInfo(id);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            infomationService.deleteInfo(id);
            rxResult.setStatus(true);
            rxResult.setMessage("delete");
            rxResult.setContent("Delete relation info success");
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("Delete info failure");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;

    }

    @Override
    public InfomationEventBus updateInfo(JsonObject infomation, Handler<AsyncResult<RxResult>> readyHandler) {
        Information information = infomation.mapTo(Information.class);
        InternalResult internalResult = infomationService.updateInfo(information);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setContent(internalResult);
            rxResult.setStatus(true);
            rxResult.setMessage("thành công");
        } else {
            rxResult.setMessage("error");
            rxResult.setStatus(false);
            rxResult.setContent(internalResult.hasErrors());
        }

        return this;
    }

    @Override
    public InfomationEventBus getAll(JsonObject information, Handler<AsyncResult<RxResult>> readyHandler) {
        JsonObject result = new JsonObject();
        try {
            result = infomationService.getAll(information);
            result.put("status",true);
        } catch (IOException e) {
            result.put("status",false);
            e.printStackTrace();
        }
        return this;
    }
}
