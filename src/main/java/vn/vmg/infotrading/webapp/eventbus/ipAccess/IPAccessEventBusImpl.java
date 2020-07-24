package vn.vmg.infotrading.webapp.eventbus.ipAccess;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.service.IPAccessService;

import java.util.List;

public class IPAccessEventBusImpl implements IPAccessEventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(IPAccessEventBusImpl.class);
    private final IPAccessService ipAccessService;

    public IPAccessEventBusImpl(IPAccessService ipAccessService, Handler<AsyncResult<IPAccessEventBus>> readyHandler) {
        this.ipAccessService = ipAccessService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public IPAccessEventBus insertIpAccess(JsonObject ipAccess, Handler<AsyncResult<RxResult>> readyHandler) {
        IPAccess ipAccess1 = ipAccess.mapTo(IPAccess.class);
        InternalResult internalResult = ipAccessService.insertIpAccess(ipAccess1);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("không thành công!");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public IPAccessEventBus getIpAccessByIp(String ip, Handler<AsyncResult<RxResult>> resultHandler) {
        IPAccess internalResult = ipAccessService.getIpAccessByIp(ip);
        RxResult rxResult = new RxResult();
        if (internalResult.getIp().length()>0) {
            rxResult.setMessage("thành công");
            rxResult.setStatus(true);
            rxResult.setContent(internalResult);
        } else {
            rxResult.setStatus(false);
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(resultHandler));
        return this;
    }

    @Override
    public IPAccessEventBus updateIPAccess(JsonObject ipAccess, Handler<AsyncResult<RxResult>> readyHandler) {
        IPAccess ipAccess1 = ipAccess.mapTo(IPAccess.class);
        InternalResult internalResult = ipAccessService.update(ipAccess1);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("không thành công!");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }

    @Override
    public IPAccessEventBus delete(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        InternalResult internalResult = ipAccessService.delete(id);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            ipAccessService.delete(id);
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
    public IPAccessEventBus findById(Integer id, Handler<AsyncResult<RxResult>> readyHandler) {
        List<IPAccess> ipAccesses = ipAccessService.findById(id);
        RxResult rxResult = new RxResult();
        if (ipAccesses.size() > 0) {
            rxResult.setContent(ipAccesses);
            rxResult.setStatus(true);
            rxResult.setMessage("thành công");
        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("error");
            rxResult.setContent(ipAccesses);
        }
        return this;
    }


}
