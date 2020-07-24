package vn.vmg.infotrading.webapp.internal.partner.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.common.WebAppResponse;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.repository.IpAccessRepository;
import vn.vmg.infotrading.webapp.internal.ipAccess.service.IPAccessService;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerRequest;
import vn.vmg.infotrading.webapp.internal.partner.validator.PartnerValidator;
import vn.vmg.infotrading.webapp.repository.IPAccessOracleRespository;
import vn.vmg.infotrading.webapp.repository.PartnerOracleRepository;

import java.util.List;

@Service
public class PartnerService {
    @Autowired
    private PartnerOracleRepository partnerRepository;
    @Autowired
    private PartnerValidator partnerValidator;

    public InternalResult insertPartner(PartnerRequest partnerRequest) {
        InternalResult internalResult = partnerValidator.valid(partnerRequest);
        if (!internalResult.hasErrors()) {
            try {
                PartnerRequest record = partnerRepository.save(partnerRequest);
                internalResult.setStatus(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                internalResult.setStatus(false);
            }

        } else {
            internalResult.setStatus(false);

        }
        return internalResult;
    }

    public InternalResult update(PartnerRequest partnerRequest) {
        InternalResult internalResult = partnerValidator.valid(partnerRequest);
        if (!internalResult.hasErrors()) {
            try {
                PartnerRequest record = partnerRepository.update(partnerRequest);
                internalResult.setStatus(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                internalResult.setStatus(false);
            }

        } else {
            internalResult.setStatus(false);

        }
        return internalResult;
    }

    public List<PartnerRequest> getPartnerByParentId(Integer parentId) {

        InternalResult internalResult = partnerValidator.valid(parentId);
        if (!internalResult.hasErrors()) {
            List<PartnerRequest> record = partnerRepository.findByParentPartner(parentId);
            internalResult.setStatus(true);
        }
        return partnerRepository.findByParentPartner(parentId);
    }

    public JsonObject getAllPartner(JsonObject partnerRequest) {
        PartnerRequest params = new PartnerRequest(partnerRequest);
        JsonObject res = new JsonObject();
        Integer total = partnerRepository.getRecordsTotal();
        res.put("recordsTotal", total);
        res.put("recordsFiltered", total);
        res.put("data", partnerRepository.getAllPartner(params));
        return res;

    }

}
