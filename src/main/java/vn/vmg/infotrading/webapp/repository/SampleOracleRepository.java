package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.sample.domain.RegisterInfo;
import vn.vmg.infotrading.webapp.internal.sample.repository.SampleRepository;

@Repository
public class SampleOracleRepository implements SampleRepository {


    private JdbcTemplate jdbcTemplate;

    @Override
    public void addCustomer(String name, String email) {
        //TODO call procedure or function here
    }

    @Override
    public Object findByUsername(String username) {
        //TODO call procedure or function here
        return null;
    }

    @Override
    public int save(RegisterInfo info) {
        //TODO call procedure or function here
        return 0;
    }
}
