package vn.vmg.infotrading.webapp.repository;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroupSearch;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.repository.InfomationGroupRepository;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static vn.vmg.infotrading.webapp.internal.InfomationGroup.query.InformationGroupQuery.*;
import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.O_PARTNERS;


@Repository
public class InfomationGroupOracleRepository implements InfomationGroupRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public InformationGroup insertInfoGroup(InformationGroup infomationGroup) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(CREATE_INFO_GROUP)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_DESCRIPTION, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_CODE, infomationGroup.getCode());
        inParamMap.put(I_DESCRIPTION, infomationGroup.getDescription());
        inParamMap.put(I_STATUS, infomationGroup.getStatus());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGINFO)
                .withProcedureName(CREATE_INFO)
                .declareParameters(
                        new SqlParameter(I_FIELD_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_INFO_GROUP_ID, Types.NUMERIC)
                );
        Information information = infomationGroup.getInformation();
        Map<String, Object> input = new HashMap<String, Object>();
        input.put(I_FIELD_NAME, information.getFieldName());
        input.put(I_PARTNER_ID, information.getPartnerId());
        input.put(I_INFO_GROUP_ID, information.getInforGroupId());
        SqlParameterSource in1 = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult1 = simpleJdbcCall.execute(in1);

        return infomationGroup;
    }


    @Override
    public List<InformationGroup> getById(Integer id) {
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
        List<InformationGroup> infomationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        return infomationGroups;
    }

    @Override
    public void deleteInfoGroup(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(DELETE_INFO_GROUP)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        simpleJdbcCall.execute(input);
    }

    @Override
    public List<InformationGroup> searchByCode(String code) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_BY_CODE)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR)

                );
        Map<String, String> input = new HashMap<String, String>();
        input.put(I_CODE, code);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<InformationGroup> infomationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        return infomationGroups;
    }

    @Override
    public InformationGroup findByCode(String code) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_BY_CODE)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR)

                );
        Map<String, String> input = new HashMap<String, String>();
        input.put(I_CODE, code);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<InformationGroup> infomationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        if (infomationGroups.size() > 0) {
            return infomationGroups.get(0);
        } else {
            return new InformationGroup();
        }
    }

    @Override
    public List<InformationGroup> searchByDescription(String desciption) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(SEARCH_BY_DESCRIPTION)
                .declareParameters(
                        new SqlParameter(I_DESCRIPTION, Types.NVARCHAR)
                );
        Map<String, String> input = new HashMap<String, String>();
        input.put(I_DESCRIPTION, desciption);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<InformationGroup> infomationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        return infomationGroups;

    }

    @Override
    public List<InformationGroup> searchByStatus(Integer status) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(SEARCH_BY_STATUS)
                .declareParameters(
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<>();
        input.put(I_STATUS, status);
        SqlParameterSource in = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        List<InformationGroup> infomationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        return infomationGroups;

    }

    @Override
    public InformationGroup updateInfoGroup(InformationGroup infomationGroup) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(UPADTE_INFO_GROUP)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC),
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_DESCRIPTION, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_ID, infomationGroup.getId());
        inParamMap.put(I_CODE, infomationGroup.getCode());
        inParamMap.put(I_DESCRIPTION, infomationGroup.getDescription());
        inParamMap.put(I_STATUS, infomationGroup.getStatus());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }


        return infomationGroup;
    }

    @Override
    public Integer searchInfoGroup(InformationGroupSearch search) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(SEARCH_INFO_GROUPS)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_DESCRIPTION, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_CODE, (JsonUtils.validParamSearch(search.getCode()) ? search.getCode() : null));
        inParamMap.put(I_DESCRIPTION, (JsonUtils.validParamSearch(search.getDescription()) ? search.getDescription() : null));
        inParamMap.put(I_STATUS, (JsonUtils.validParamSearch(search.getStatus()) ? Integer.valueOf(search.getStatus()) : null));
        Map<String, Object> map = simpleJdbcCall.execute(inParamMap);
        return Integer.valueOf(((List<Map<String, Object>>) map.get(O_INFO_GROUP)).get(0).get("results").toString());

    }

    @Override
    public List<JsonObject> getAll(InformationGroup infomationGroup) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(SEARCH_INFO_GROUPS)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_DESCRIPTION, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_CODE, (JsonUtils.validParamSearch(infomationGroup.getCode()) ? infomationGroup.getCode() : null));
        inParamMap.put(I_DESCRIPTION, (JsonUtils.validParamSearch(infomationGroup.getDescription()) ? infomationGroup.getDescription() : null));
        inParamMap.put(I_STATUS, (JsonUtils.validParamSearch(infomationGroup.getStatus()) ? Integer.valueOf(infomationGroup.getStatus()) : null));
        Map<String, Object> map = simpleJdbcCall.execute(inParamMap);
        List<JsonObject> responseList = ((List<Map<String, Object>>) map.get(O_PARTNERS))
                .stream().map(stringObjectMap ->
                        new InformationGroupSearch(stringObjectMap, false).toJson()).collect(Collectors.toList());
        return responseList;

    }

    @Override
    public Integer counting(InformationGroupSearch infomationGroupSearch) {
        return null;
    }

    @Override
    public InformationGroup findById(Integer id) {
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
        List<InformationGroup> informationGroups = ((List<Map<String, Object>>) simpleJdbcCallResult.get(O_INFO_GROUP))
                .stream().map(stringObjectMap -> new InformationGroup(stringObjectMap, true)).collect(Collectors.toList());
        if (informationGroups.size() > 0) {
            return informationGroups.get(0);
        } else {
            return new InformationGroup();
        }
    }

    @Override
    public Integer getRecordsTotal() {

        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_ALL_INFO_GROUP);

        Map<String, Object> input = new HashMap<String, Object>();
        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Integer.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("recordsTotal").toString());

    }

    public boolean isCodeExist(String code) {
        return this.searchByCode(code) != null;
    }
}
