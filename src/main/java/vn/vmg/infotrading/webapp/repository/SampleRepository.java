package vn.vmg.infotrading.webapp.repository;

import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.sample.domain.Customer;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class SampleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public List<Customer> findAll() {
//        System.out.println("call users");
//        List<Customer> result = jdbcTemplate.query(
//            "SELECT id, name, username, email FROM users",
//            (rs, rowNum) -> new Customer(
//                rs.getInt("id"),
//                rs.getString("name"),
//                rs.getString("username"),
//                rs.getString("email"))
//        );
//        return result;
//    }

    public List<Customer> findAll() {
        List paramList = new ArrayList();
        paramList.add(new SqlOutParameter("o_users", OracleTypes.CURSOR));
        paramList.add(new SqlParameter(OracleTypes.NUMERIC));
        paramList.add(new SqlParameter(OracleTypes.NVARCHAR));

        Map<String, Object> mapResults = jdbcTemplate.call(new SamplePrcGetUsers(), paramList);

        List<Map<String, Object>> listResults = (List<Map<String, Object>>) mapResults.get("o_users");

        List<Customer> customers = listResults.stream().map(this::transform).collect(Collectors.toList());

        return customers;
    }

    private Customer transform(Map<String, Object> data) {
        int id = Integer.valueOf(data.get("ID").toString());
        String name = data.get("NAME").toString();
        return new Customer(id, name);
    }

    public void addCustomer(String name, String email) {
        jdbcTemplate.update("INSERT INTO customer(name, email, created_date) VALUES (?,?,?)",
            name, email, new Date());

    }
}
