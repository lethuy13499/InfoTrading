package vn.vmg.infotrading.webapp.internal.ipAccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.validator.DeleteIPAccessValidator;
import vn.vmg.infotrading.webapp.internal.ipAccess.validator.IPAccessValidator;
import vn.vmg.infotrading.webapp.internal.ipAccess.validator.UpdateIPAccessValidator;
import vn.vmg.infotrading.webapp.repository.IPAccessOracleRespository;

import java.util.List;


@Service
public class IPAccessService {
    @Autowired
    private UpdateIPAccessValidator updateIPAccessValidator;
    @Autowired
    private DeleteIPAccessValidator deleteIPAccessValidator;
    @Autowired
    private IPAccessOracleRespository ipAccessRespository;
    @Autowired
    private IPAccessValidator ipAccessValidator;

    public IPAccess getIpAccessByIp(String ip) {
        return ipAccessRespository.getByIp(ip);
    }

    @Transactional(rollbackFor = Exception.class)
    public InternalResult insertIpAccess(IPAccess ipAccess) {
        InternalResult internalResult = ipAccessValidator.valid(ipAccess);
        if (!internalResult.hasErrors()) {
            IPAccess record = ipAccessRespository.insertIP(ipAccess);
            internalResult.setStatus(true);
        }
        return internalResult;

    }

    @Transactional(rollbackFor = Exception.class)
    public InternalResult update(IPAccess ipAccess) {
        InternalResult internalResult = updateIPAccessValidator.valid(ipAccess);
        if (!internalResult.hasErrors()) {
            IPAccess record = ipAccessRespository.update(ipAccess);
            internalResult.setStatus(true);
        }
        return internalResult;
    }

    public InternalResult delete(Integer id) {
        InternalResult internalResult = deleteIPAccessValidator.valid(id);
        if (internalResult.hasErrors()) {
            internalResult.setStatus(true);
        }

        ipAccessRespository.deleteIpAccess(id);
        return internalResult;

    }

    public IPAccess getById(Integer id) {
        return ipAccessRespository.getById(id);
    }

    public List<IPAccess> findById(Integer id) {
        return ipAccessRespository.findById(id);
    }

}
