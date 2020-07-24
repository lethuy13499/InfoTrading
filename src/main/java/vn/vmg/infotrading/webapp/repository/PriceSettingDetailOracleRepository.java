package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetail;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetailModel;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.repository.PriceSettingDetailRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PriceSettingDetailOracleRepository implements PriceSettingDetailRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<PriceSettingDetail> getAllByPriceSettingId(Long priceSettingId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTING_DETAILS)
                .withProcedureName(PRC_GET_ALL_BY_PRICE_SETTING_ID)
                .declareParameters(
                        new SqlParameter("i_price_setting_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_price_setting_id", priceSettingId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<PriceSettingDetail> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new PriceSettingDetail(obj)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Long insert(PriceSettingDetailModel priceSettingDetailModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTING_DETAILS)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_information_group_id", Types.NUMERIC),
                        new SqlParameter("i_information_field", Types.NVARCHAR),
                        new SqlParameter("i_count_method", Types.NUMERIC),
                        new SqlParameter("i_require_list", Types.NVARCHAR),
                        new SqlParameter("i_price_setting_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_information_group_id", priceSettingDetailModel.getInformationGroupId());
        input.put("i_information_field", priceSettingDetailModel.getInformationField());
        input.put("i_count_method", priceSettingDetailModel.getCountMethod());
        input.put("i_require_list", priceSettingDetailModel.getRequireList());
        input.put("i_price_setting_id", priceSettingDetailModel.getPriceSettingId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void update(PriceSettingDetailModel priceSettingDetailModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTING_DETAILS)
                .withProcedureName(PRC_UPDATE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_information_group_id", Types.NUMERIC),
                        new SqlParameter("i_information_field", Types.NVARCHAR),
                        new SqlParameter("i_count_method", Types.NUMERIC),
                        new SqlParameter("i_require_list", Types.NVARCHAR),
                        new SqlParameter("i_update_time", Types.TIMESTAMP),
                        new SqlParameter("i_price_setting_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", priceSettingDetailModel.getId());
        input.put("i_information_group_id", priceSettingDetailModel.getInformationGroupId());
        input.put("i_information_field", priceSettingDetailModel.getInformationField());
        input.put("i_count_method", priceSettingDetailModel.getCountMethod());
        input.put("i_require_list", priceSettingDetailModel.getRequireList());
        input.put("i_update_time", priceSettingDetailModel.getUpdateTime());
        input.put("i_price_setting_id", priceSettingDetailModel.getPriceSettingId());

        simpleJdbcCall.execute(input);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTING_DETAILS)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

    @Override
    public void deleteByPriceSettingId(Long priceSettingId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_PRICE_SETTING_DETAILS)
                .withProcedureName(PRC_DELETE_BY_PRICE_SETTING_ID)
                .declareParameters(
                        new SqlParameter("i_price_setting_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_price_setting_id", priceSettingId);

        simpleJdbcCall.execute(input);
    }

}
