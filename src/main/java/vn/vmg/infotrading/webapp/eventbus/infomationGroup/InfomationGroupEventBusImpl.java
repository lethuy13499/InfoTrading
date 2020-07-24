package vn.vmg.infotrading.webapp.eventbus.infomationGroup;

import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.service.InfomationGroupService;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.io.IOException;
import java.util.List;


public class InfomationGroupEventBusImpl implements InfomationGroupEventBus {
    private final InfomationGroupService infomationGroupService;
    private static final Logger LOGGER = LoggerFactory.getLogger(InfomationGroupEventBusImpl.class);

    public InfomationGroupEventBusImpl(InfomationGroupService infomationGroupService, Handler<AsyncResult<InfomationGroupEventBus>> readyHandler) {
        this.infomationGroupService = infomationGroupService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public InfomationGroupEventBus insertInfoGroup(JsonObject infoGroup, Handler<AsyncResult<RxResult>> readyHandler) {
        InformationGroup infomationGroup = infoGroup.mapTo(InformationGroup.class);
        InternalResult internalResult = infomationGroupService.insertInfoGroup(infomationGroup);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
            rxResult.setMessage("Insert thành công");
            rxResult.setContent(internalResult);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("insert không thành công");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public InfomationGroupEventBus searchByCode(String code, Handler<AsyncResult<RxResult>> readyHandler) {
        List<InformationGroup> internalResult = infomationGroupService.searchByCode(code);
        RxResult rxResult = new RxResult();
        if (internalResult.size() > 0) {
            rxResult.setStatus(true);
            rxResult.setContent(internalResult);
            rxResult.setMessage("Thành công");

        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("Error");
            rxResult.setContent(internalResult);
        }
        return this;
    }

    @Override
    public InfomationGroupEventBus updateInfoGroup(JsonObject infoGroup, Handler<AsyncResult<RxResult>> readyHandler) {
        InformationGroup infomationGroup = infoGroup.mapTo(InformationGroup.class);
        InternalResult internalResult = infomationGroupService.insertInfoGroup(infomationGroup);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
            rxResult.setMessage("update thành công");
            rxResult.setContent(infoGroup);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("update không thành công");
            rxResult.setContent(internalResult.getErrors());
        }
        return this;
    }

    @Override
    public InfomationGroupEventBus searchByDescription(String description, Handler<AsyncResult<RxResult>> readyHandler) {
        List<InformationGroup> internalResult = infomationGroupService.searchByDescription(description);
        RxResult rxResult = new RxResult();
        if (internalResult.size() > 0) {
            rxResult.setStatus(true);
            rxResult.setMessage("Search thành công");
            rxResult.setContent(internalResult);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("error");
            rxResult.setContent(internalResult);
        }
        return this;
    }

    @Override
    public InfomationGroupEventBus searchByStatus(Integer status, Handler<AsyncResult<RxResult>> readyHandler) {
        List<InformationGroup> infomationGroup = infomationGroupService.searchByStatus(status);
        RxResult rxResult = new RxResult();
        if (infomationGroup.size() > 0) {
            rxResult.setStatus(true);
            rxResult.setMessage("thành công");
            rxResult.setContent(infomationGroup);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("error");
            rxResult.setContent(infomationGroup);
        }
        return null;
    }

    @Override
    public InfomationGroupEventBus getById(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        List<InformationGroup> infomationGroup = infomationGroupService.getById(id);
        RxResult rxResult = new RxResult();
        if (infomationGroup.size() > 0) {
            rxResult.setContent(infomationGroup);
            rxResult.setStatus(true);
            rxResult.setMessage("thành công");
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("error");
            rxResult.setContent(infomationGroup);
        }

        return this;
    }

    @Override
    public InfomationGroupEventBus deleteInfoGroup(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        InternalResult internalResult = infomationGroupService.deleteInfoGroup(id);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            infomationGroupService.deleteInfoGroup(id);
            rxResult.setStatus(true);
            rxResult.setMessage("delete");
            rxResult.setContent("Delete information group success");
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("Delete information group failure");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public InfomationGroupEventBus searchInfoGroup(JsonObject infoGroups, Handler<AsyncResult<RxResult>> readyHandler) {
       // JsonObject jsonObject = infomationGroupService.searchInfoGroup(infoGroups);

        return null;
    }

    @Override
    public InfomationGroupEventBus getAll(JsonObject jsonObject, Handler<AsyncResult<RxResult>> readyHandler) {
        JsonObject result = new JsonObject();

        try {
            result = infomationGroupService.getAll(jsonObject);
            result.put("status",true);
        } catch (IOException e) {
            result.put("status", false);
            e.printStackTrace();
        }
        //Single.just(result).subscribe((BiConsumer<? super JsonObject, ? super Throwable>) SingleHelper.toObserver(readyHandler));
        return this;
    }


}