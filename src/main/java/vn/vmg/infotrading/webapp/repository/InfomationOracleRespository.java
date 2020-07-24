package vn.vmg.infotrading.webapp.repository;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.infomation.repository.InformationRepository;
import vn.vmg.infotrading.webapp.internal.infomation.service.InformationService;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static vn.vmg.infotrading.webapp.internal.infomation.query.InformationQuery.*;

@Repository
public class InfomationOracleRespository implements InformationRepository {
    @Autowired
    private InformationService infomationService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public Information insertInfo(Information info) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(CREATE_INFO)
                .declareParameters(
                        new SqlParameter(I_FIELD_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_INFO_GROUP_ID, Types.NUMERIC)

                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_FIELD_NAME, info.getFieldName());
        inParamMap.put(I_PARTNER_ID, info.getPartnerId());
        inParamMap.put(I_INFO_GROUP_ID, info.getInforGroupId());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }

        return info;
    }

    @Override
    public List<Information> getById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_BY_ID)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<Information> infomation = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO))
                .stream().map(stringObjectMap -> new Information(stringObjectMap, true)).collect(Collectors.toList());
        return infomation;
    }

    @Override
    public void deleteInfo(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(DELETE_INFO)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        simpleJdbcCall.execute(input);

    }

    @Override
    public Information updateInfo(Information information) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(UPDATE_INFO)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC),
                        new SqlParameter(I_FIELD_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_INFO_GROUP_ID, Types.NUMERIC)

                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_ID, information.getId());
        inParamMap.put(I_FIELD_NAME, information.getFieldName());
        inParamMap.put(I_PARTNER_ID, information.getPartnerId());
        inParamMap.put(I_INFO_GROUP_ID, information.getInforGroupId());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }


        return information;
    }

    @Override
    public List<JsonObject> getAll(Information information) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_ALL)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_field_name", Types.NVARCHAR),
                        new SqlParameter("i_partner_id", Types.NUMERIC),
                        new SqlParameter("infor_group_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_id", information.getId());
        input.put("i_field_name", information.getFieldName());
        input.put("i_partner_id", information.getPartnerId());
        input.put("infor_group_id", information.getInforGroupId());
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<JsonObject> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new Information(obj, true).toJson()).collect(Collectors.toList());
        return result;
    }

    @Override
    public Information findById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_BY_ID)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<Information> information = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO))
                .stream().map(stringObjectMap -> new Information(stringObjectMap, true)).collect(Collectors.toList());
        if (information.size() > 0) {
            return information.get(0);
        } else {
            return new Information();
        }
    }

    @Override
    public Integer getRecordsTotal(Information information) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(COUNT_INFORMATION)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC),
                        new SqlParameter(I_FIELD_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_INFO_GROUP_ID, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_ID, information.getId());
        inParamMap.put(I_FIELD_NAME, information.getFieldName());
        inParamMap.put(I_PARTNER_ID, information.getPartnerId());
        inParamMap.put(I_INFO_GROUP_ID, information.getInforGroupId());
        Map<String, Object> map = simpleJdbcCall.execute(inParamMap);
        return Integer.valueOf(((List<Map<String, Object>>) map.get(O_INFO)).get(0).get("results").toString());

    }

}
