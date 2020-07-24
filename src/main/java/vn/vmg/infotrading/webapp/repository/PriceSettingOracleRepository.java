package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSetting;
import vn.vmg.infotrading.webapp.internal.price_setting.domain.PriceSettingModel;
import vn.vmg.infotrading.webapp.internal.price_setting.repository.PriceSettingRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PriceSettingOracleRepository implements PriceSettingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<PriceSetting> getAllByAppendixId(Long appendixId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTINGS)
                .withProcedureName(PRC_GET_ALL_BY_APPENDIX_ID)
                .declareParameters(
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_appendix_id", appendixId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<PriceSetting> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new PriceSetting(obj)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Long insert(PriceSettingModel priceSettingModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTINGS)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_validity_time", Types.TIMESTAMP),
                        new SqlParameter("i_expiry_time", Types.TIMESTAMP),
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_validity_time", priceSettingModel.getValidityTime());
        input.put("i_expiry_time", priceSettingModel.getExpiryTime());
        input.put("i_appendix_id", priceSettingModel.getAppendixId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void update(PriceSettingModel priceSettingModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTINGS)
                .withProcedureName(PRC_UPDATE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_update_time", Types.TIMESTAMP),
                        new SqlParameter("i_validity_time", Types.TIMESTAMP),
                        new SqlParameter("i_expiry_time", Types.TIMESTAMP),
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", priceSettingModel.getId());
        input.put("i_update_time", priceSettingModel.getUpdateTime());
        input.put("i_validity_time", priceSettingModel.getValidityTime());
        input.put("i_expiry_time", priceSettingModel.getExpiryTime());
        input.put("i_appendix_id", priceSettingModel.getAppendixId());

        simpleJdbcCall.execute(input);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTINGS)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }
}
