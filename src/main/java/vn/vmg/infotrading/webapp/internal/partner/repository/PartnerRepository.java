package vn.vmg.infotrading.webapp.internal.partner.repository;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerRequest;

import java.util.List;

public interface PartnerRepository {
    PartnerRequest save(PartnerRequest partnerRequest);

    List<PartnerRequest> findByParentPartner(Integer partnerId);

    List<JsonObject> getAllPartner(PartnerRequest partnerRequest);

    List<PartnerRequest> findById(Integer id);

    PartnerRequest getById(Integer id);

    PartnerRequest update(PartnerRequest partnerRequest);

    Integer getRecordsTotal();
}
