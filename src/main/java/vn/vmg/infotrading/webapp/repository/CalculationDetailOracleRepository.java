package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetail;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;
import vn.vmg.infotrading.webapp.internal.calculation_detail.repository.CalculationRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CalculationDetailOracleRepository implements CalculationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<CalculationDetail> getAllByPriceSettingDetailId(Long priceSettingDetailId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CALCULATION_DETAILS)
                .withProcedureName(PRC_GET_ALL_BY_PRICE_SETTING_DETAIL_ID)
                .declareParameters(
                        new SqlParameter("i_price_setting_detail_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_price_setting_detail_id", priceSettingDetailId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<CalculationDetail> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new CalculationDetail(obj)).collect(Collectors.toList());
        return result;
    }

    @Override
    public Long insert(CalculationDetailModel calculationDetailModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CALCULATION_DETAILS)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_calculation_method", Types.NUMERIC),
                        new SqlParameter("i_commitment_input_quantity_frame", Types.VARCHAR),
                        new SqlParameter("i_success_output_quantity_frame", Types.VARCHAR),
                        new SqlParameter("i_unit_price", Types.NUMERIC),
                        new SqlParameter("i_price_setting_detail_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_calculation_method", calculationDetailModel.getCalculationMethod());
        input.put("i_commitment_input_quantity_frame", calculationDetailModel.getCommitmentInputQuantityFrame());
        input.put("i_success_output_quantity_frame", calculationDetailModel.getSuccessOutputQuantityFrame());
        input.put("i_unit_price", calculationDetailModel.getUnitPrice());
        input.put("i_price_setting_detail_id", calculationDetailModel.getPriceSettingDetailId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void update(CalculationDetailModel calculationDetailModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CALCULATION_DETAILS)
                .withProcedureName(PRC_UPDATE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_calculation_method", Types.NUMERIC),
                        new SqlParameter("i_commitment_input_quantity_frame", Types.VARCHAR),
                        new SqlParameter("i_success_output_quantity_frame", Types.VARCHAR),
                        new SqlParameter("i_unit_price", Types.NUMERIC),
                        new SqlParameter("i_update_time", Types.TIMESTAMP),
                        new SqlParameter("i_price_setting_detail_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", calculationDetailModel.getId());
        input.put("i_calculation_method", calculationDetailModel.getCalculationMethod());
        input.put("i_commitment_input_quantity_frame", calculationDetailModel.getCommitmentInputQuantityFrame());
        input.put("i_success_output_quantity_frame", calculationDetailModel.getSuccessOutputQuantityFrame());
        input.put("i_unit_price", calculationDetailModel.getUnitPrice());
        input.put("i_update_time", calculationDetailModel.getUpdateTime());
        input.put("i_price_setting_detail_id", calculationDetailModel.getPriceSettingDetailId());

        simpleJdbcCall.execute(input);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CALCULATION_DETAILS)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

    @Override
    public void deleteByPriceSettingDetailId(Long priceSettingDetailId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_CALCULATION_DETAILS)
                .withProcedureName(PRC_DELETE_BY_PRICE_SETTING_DETAIL_ID)
                .declareParameters(
                        new SqlParameter("i_price_setting_detail_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_price_setting_detail_id", priceSettingDetailId);

        simpleJdbcCall.execute(input);
    }
}
