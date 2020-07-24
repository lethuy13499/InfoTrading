package vn.vmg.infotrading.webapp.repository;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryData;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryDataModel;
import vn.vmg.infotrading.webapp.internal.category_data.repository.CategoryDataRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CategoryDataOracleRepository implements CategoryDataRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<JsonObject> getAll(CategoryDataModel categoryDataModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_GET_ALL)
            .declareParameters(
                new SqlParameter("i_code", Types.VARCHAR),
                new SqlParameter("i_name", Types.NVARCHAR),
                new SqlParameter("i_json", Types.NVARCHAR),
                new SqlParameter("i_common_search", Types.NVARCHAR),
                new SqlParameter("i_status", Types.NUMERIC),
                new SqlParameter("i_category_id", Types.NUMERIC),
                new SqlParameter("i_order_by", Types.NVARCHAR),
                new SqlParameter("i_order_type", Types.NVARCHAR),
                new SqlParameter("i_skip", Types.NUMERIC),
                new SqlParameter("i_take", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_code", categoryDataModel.getCode());
        input.put("i_name", categoryDataModel.getName());
        input.put("i_json", categoryDataModel.getJson());
        input.put("i_common_search", categoryDataModel.getCommonSearch());
        input.put("i_status", categoryDataModel.getStatus());
        input.put("i_category_id", categoryDataModel.getCategoryId());
        input.put("i_order_by", categoryDataModel.getOrderBy());
        input.put("i_order_type", categoryDataModel.getOrderType());
        input.put("i_skip", categoryDataModel.getSkip());
        input.put("i_take", categoryDataModel.getTake());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<JsonObject> result = ((List<Map<String, Object>>) map.get("o_res"))
            .stream().map(obj -> new CategoryData(obj).toJson()).collect(Collectors.toList());
        return result;
    }

    @Override
    public CategoryData getById(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_GET_BY_ID)
            .declareParameters(
                new SqlParameter("i_id", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_id", id);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        if (((List<Map<String, Object>>) map.get("o_res")).size() > 0) {
            List<CategoryData> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new CategoryData(obj)).collect(Collectors.toList());
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer getRecordsTotal(Long categoryId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_RECORDS_TOTAL).declareParameters(
                new SqlParameter("i_category_id", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_category_id", categoryId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Integer.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("recordsTotal").toString());
    }

    @Override
    public Long insert(CategoryDataModel categoryDataModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_INSERT)
            .declareParameters(
                new SqlParameter("i_code", Types.VARCHAR),
                new SqlParameter("i_name", Types.NVARCHAR),
                new SqlParameter("i_json", Types.NVARCHAR),
                new SqlParameter("i_status", Types.NUMERIC),
                new SqlParameter("i_creator_id", Types.NUMERIC),
                new SqlParameter("i_category_id", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<>();
        input.put("i_code", categoryDataModel.getCode());
        input.put("i_name", categoryDataModel.getName());
        input.put("i_json", categoryDataModel.getJson());
        input.put("i_status", categoryDataModel.getStatus());
        input.put("i_creator_id", categoryDataModel.getCreatorId());
        input.put("i_category_id", categoryDataModel.getCategoryId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void update(CategoryDataModel categoryDataModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_UPDATE)
            .declareParameters(
                new SqlParameter("i_id", Types.NUMERIC),
                new SqlParameter("i_code", Types.VARCHAR),
                new SqlParameter("i_name", Types.NVARCHAR),
                new SqlParameter("i_json", Types.NVARCHAR),
                new SqlParameter("i_status", Types.NUMERIC),
                new SqlParameter("i_creator_id", Types.NUMERIC),
                new SqlParameter("i_category_id", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", categoryDataModel.getId());
        input.put("i_code", categoryDataModel.getCode());
        input.put("i_name", categoryDataModel.getName());
        input.put("i_json", categoryDataModel.getJson());
        input.put("i_status", categoryDataModel.getStatus());
        input.put("i_creator_id", categoryDataModel.getCreatorId());
        input.put("i_category_id", categoryDataModel.getCategoryId());

        simpleJdbcCall.execute(input);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withCatalogName(PKG_CATEGORY_DATAS)
            .withProcedureName(PRC_DELETE)
            .declareParameters(
                new SqlParameter("i_id", Types.NUMERIC)
            );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

}
