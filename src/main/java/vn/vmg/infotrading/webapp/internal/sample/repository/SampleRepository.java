package vn.vmg.infotrading.webapp.internal.sample.repository;

import vn.vmg.infotrading.webapp.internal.sample.domain.RegisterInfo;

public interface SampleRepository {
    public void addCustomer(String name, String email);

    Object findByUsername(String username);

    int save(RegisterInfo info);
}
