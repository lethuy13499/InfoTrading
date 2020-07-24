package vn.vmg.infotrading.webapp.repository;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.appendix.domain.Appendix;
import vn.vmg.infotrading.webapp.internal.appendix.domain.AppendixModel;
import vn.vmg.infotrading.webapp.internal.appendix.repository.AppendixRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AppendixOracleRepository implements AppendixRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<JsonObject> getAll(AppendixModel appendixModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_GET_ALL)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_partner_id", Types.NUMERIC),
                        new SqlParameter("i_contract_id", Types.NUMERIC),
                        new SqlParameter("i_contract_code", Types.VARCHAR),
                        new SqlParameter("i_type", Types.NUMERIC),
                        new SqlParameter("i_create_time", Types.TIMESTAMP),
                        new SqlParameter("i_order_by", Types.NVARCHAR),
                        new SqlParameter("i_order_type", Types.NVARCHAR),
                        new SqlParameter("i_skip", Types.NUMERIC),
                        new SqlParameter("i_take", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_id", appendixModel.getId());
        input.put("i_partner_id", appendixModel.getPartnerId());
        input.put("i_contract_id", appendixModel.getContractId());
        input.put("i_contract_code", appendixModel.getContractCode());
        input.put("i_type", appendixModel.getType());
        input.put("i_create_time", appendixModel.getCreateTime());
        input.put("i_order_by", appendixModel.getOrderBy());
        input.put("i_order_type", appendixModel.getOrderType());
        input.put("i_skip", appendixModel.getSkip());
        input.put("i_take", appendixModel.getTake());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<JsonObject> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new Appendix(obj).toJson()).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<Appendix> getAllByContractId(Long contractId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_GET_ALL_BY_CONTRACT_ID)
                .declareParameters(
                        new SqlParameter("i_contract_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_contract_id", contractId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<Appendix> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new Appendix(obj)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Appendix getById(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_GET_BY_ID)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_id", id);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        if (((List<Map<String, Object>>) map.get("o_res")).size() > 0) {
            List<Appendix> result = ((List<Map<String, Object>>) map.get("o_res"))
                    .stream().map(obj -> new Appendix(obj)).collect(Collectors.toList());
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Appendix getByCode(String code) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_GET_BY_CODE)
                .declareParameters(
                        new SqlParameter("i_code", Types.VARCHAR)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_code", code);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        if (((List<Map<String, Object>>) map.get("o_res")).size() > 0) {
            List<Appendix> result = ((List<Map<String, Object>>) map.get("o_res"))
                    .stream().map(obj -> new Appendix(obj)).collect(Collectors.toList());
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer getRecordsTotal() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_RECORDS_TOTAL);

        Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Integer.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("recordsTotal").toString());
    }

    @Override
    public Long insert(AppendixModel appendixModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_code", Types.VARCHAR),
                        new SqlParameter("i_type", Types.NUMERIC),
                        new SqlParameter("i_description", Types.NVARCHAR),
                        new SqlParameter("i_status", Types.NUMERIC),
                        new SqlParameter("i_create_time", Types.TIMESTAMP),
                        new SqlParameter("i_sign_time", Types.TIMESTAMP),
                        new SqlParameter("i_validity_time", Types.TIMESTAMP),
                        new SqlParameter("i_expiry_time", Types.TIMESTAMP),
                        new SqlParameter("i_contract_id", Types.NUMERIC),
                        new SqlParameter("i_partner_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_code", appendixModel.getCode());
        input.put("i_type", appendixModel.getType());
        input.put("i_description", appendixModel.getDescription());
        input.put("i_status", appendixModel.getStatus());
        input.put("i_create_time", appendixModel.getCreateTime());
        input.put("i_sign_time", appendixModel.getSignTime());
        input.put("i_validity_time", appendixModel.getValidityTime());
        input.put("i_expiry_time", appendixModel.getExpiryTime());
        input.put("i_contract_id", appendixModel.getContractId());
        input.put("i_partner_id", appendixModel.getPartnerId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void update(AppendixModel appendixModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_UPDATE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_code", Types.VARCHAR),
                        new SqlParameter("i_type", Types.NUMERIC),
                        new SqlParameter("i_description", Types.NVARCHAR),
                        new SqlParameter("i_status", Types.NUMERIC),
                        new SqlParameter("i_sign_time", Types.TIMESTAMP),
                        new SqlParameter("i_validity_time", Types.TIMESTAMP),
                        new SqlParameter("i_expiry_time", Types.TIMESTAMP),
                        new SqlParameter("i_contract_id", Types.NUMERIC),
                        new SqlParameter("i_partner_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", appendixModel.getId());
        input.put("i_code", appendixModel.getCode());
        input.put("i_type", appendixModel.getType());
        input.put("i_description", appendixModel.getDescription());
        input.put("i_status", appendixModel.getStatus());
        input.put("i_sign_time", appendixModel.getSignTime());
        input.put("i_validity_time", appendixModel.getValidityTime());
        input.put("i_expiry_time", appendixModel.getExpiryTime());
        input.put("i_contract_id", appendixModel.getContractId());
        input.put("i_partner_id", appendixModel.getPartnerId());

        simpleJdbcCall.execute(input);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

    @Override
    public void deleteByContractId(Long contractId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIXES)
                .withProcedureName(PRC_DELETE_BY_CONTRACT_ID)
                .declareParameters(
                        new SqlParameter("i_contract_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_contract_id", contractId);

        simpleJdbcCall.execute(input);
    }

    public boolean isCodeExist(String code) {
        return this.getByCode(code) != null;
    }
}
