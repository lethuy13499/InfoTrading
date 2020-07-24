package vn.vmg.infotrading.webapp.internal.ipAccess.repository;

import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;

import java.util.List;

public interface IpAccessRepository {

    IPAccess insertIP(IPAccess ipAccess);

    IPAccess getByIp(String ip);

    List<IPAccess> findById(Integer id);

    void deleteIpAccess(Integer id);

    IPAccess getById(Integer id);

    IPAccess update(IPAccess ipAccess);
}
