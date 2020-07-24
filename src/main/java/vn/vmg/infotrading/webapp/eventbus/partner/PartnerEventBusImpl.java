package vn.vmg.infotrading.webapp.eventbus.partner;

import io.reactivex.Single;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.SingleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.common.WebAppResponse;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerRequest;
import vn.vmg.infotrading.webapp.internal.partner.service.PartnerService;

import java.io.IOException;
import java.util.List;

public class PartnerEventBusImpl implements PartnerEventBus {
    private static final Logger LOGGER = LoggerFactory.getLogger(PartnerEventBusImpl.class);
    private final PartnerService partnerService;

    public PartnerEventBusImpl(PartnerService partnerService, Handler<AsyncResult<PartnerEventBus>> readyHandler) {
        this.partnerService = partnerService;
        Single.just(this).subscribe(SingleHelper.toObserver(readyHandler));
    }

    @Override
    public PartnerEventBus insertPartner(JsonObject partnerRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        PartnerRequest partnerRequest1 = partnerRequest.mapTo(PartnerRequest.class);
        InternalResult internalResult = partnerService.insertPartner(partnerRequest1);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
            rxResult.setMessage("Insert thành công");
            rxResult.setContent(internalResult);

        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("không thành công!");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }
    @Override
    public PartnerEventBus update(JsonObject partnerRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        PartnerRequest partnerRequest1 = partnerRequest.mapTo(PartnerRequest.class);
        InternalResult internalResult = partnerService.update(partnerRequest1);
        RxResult rxResult = new RxResult();
        if (internalResult.isSuccess()) {
            rxResult.setStatus(true);
            rxResult.setMessage("Insert thành công");
            rxResult.setContent(partnerService.update(partnerRequest1));

        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("không thành công!");
            rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }



    @Override
    public PartnerEventBus getPartnerByParent(Integer partnerId, Handler<AsyncResult<RxResult>> readyHandler) {

        List<PartnerRequest> internalResult = partnerService.getPartnerByParentId(partnerId);
        RxResult rxResult = new RxResult();
        if (internalResult.size() > 0) {
            rxResult.setMessage("thành công");
            rxResult.setStatus(true);
            rxResult.setContent(internalResult);

        } else {
            rxResult.setStatus(false);
            rxResult.setMessage("không thành công");
            //rxResult.setContent(internalResult.getErrors());
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;
    }


    @Override
    public PartnerEventBus getAllPartner(JsonObject partnerRequest, Handler<AsyncResult<RxResult>> readyHandler) {
        JsonObject partnerRequest1 = partnerService.getAllPartner(partnerRequest);
        RxResult rxResult = new RxResult();
        if (partnerRequest1.size() > 0) {
            rxResult.setMessage("oke");
            rxResult.setContent(partnerRequest);

        } else {
            rxResult.setMessage("fail");
        }
        Single.just(rxResult).subscribe(SingleHelper.toObserver(readyHandler));
        return this;

    }

}


