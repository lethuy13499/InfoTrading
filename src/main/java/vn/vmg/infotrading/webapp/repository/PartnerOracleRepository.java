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
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerRequest;
import vn.vmg.infotrading.webapp.internal.partner.domain.PartnerResponse;
import vn.vmg.infotrading.webapp.internal.partner.repository.PartnerRepository;
import vn.vmg.infotrading.webapp.internal.partner.service.PartnerService;

import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.*;

import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.I_IP_PARTNER;

@Repository
public class PartnerOracleRepository implements PartnerRepository {

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PartnerRequest save(PartnerRequest partnerRequest) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(CREATE_PARTNER)
                .declareParameters(
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARENT_ID, Types.NUMERIC),
                        new SqlParameter(I_SUPPLIER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_CUSTOMER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_DELEGATE, Types.NVARCHAR),
                        new SqlParameter(I_NOTE, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC),
                        new SqlParameter(I_IP_PARTNER, Types.NVARCHAR)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_CODE, partnerRequest.getCode());
        inParamMap.put(I_NAME, partnerRequest.getName());
        inParamMap.put(I_PARENT_ID, partnerRequest.getPartnerParent());
        inParamMap.put(I_SUPPLIER_TYPE, partnerRequest.getSupplierType());
        inParamMap.put(I_CUSTOMER_TYPE, partnerRequest.getCustomerType());
        inParamMap.put(I_DELEGATE, partnerRequest.getDelegate());
        inParamMap.put(I_NOTE, partnerRequest.getNote());
        inParamMap.put(I_STATUS, partnerRequest.getStatus());
        inParamMap.put(I_IP_PARTNER, partnerRequest.getIpPartner());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAMEIP)
                .withProcedureName(CREATE_PARTNER_IP_ACCESSES)
                .declareParameters(
                        new SqlParameter(I_IP_ACCESS, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_NOTE, Types.NVARCHAR)
                );
        IPAccess ipAccess = partnerRequest.getIpAccesses();
        inParamMap.put(I_IP_ACCESS, ipAccess.getIp());
        inParamMap.put(I_PARTNER_ID, ipAccess.getPartner_id());
        inParamMap.put(I_NOTE, ipAccess.getNote());
        SqlParameterSource in1 = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult1 = simpleJdbcCall.execute(in1);
        return partnerRequest;
    }

    @Override
    public List<PartnerRequest> findByParentPartner(Integer partnerId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_PARTNER_BY_ID)
                .declareParameters(
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_PARTNER_ID, partnerId);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<PartnerRequest> partnerList = ((List<Map<String, Object>>) map.get(O_PARTNERS))
                .stream().map(stringObjectMap -> new PartnerRequest(stringObjectMap, true)).collect(Collectors.toList());
        return partnerList;
    }

    @Override
    public List<JsonObject> getAllPartner(PartnerRequest partnerRequest) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_ALL_CUS)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC),
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_CUSTOMER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_SUPPLIER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_DELEGATE, Types.NVARCHAR),
                        new SqlParameter(I_NOTE, Types.NVARCHAR),
                        new SqlParameter(I_IP_PARTNER, Types.NVARCHAR)
                );

        Map<String, Object> input = new HashMap<String, Object>();
        input.put(I_ID, partnerRequest.getId());
        input.put(I_CODE, partnerRequest.getCode());
        input.put(I_NAME, partnerRequest.getName());
        input.put(I_PARENT_ID, partnerRequest.getPartnerParent());
        input.put(I_SUPPLIER_TYPE, partnerRequest.getSupplierType());
        input.put(I_CUSTOMER_TYPE, partnerRequest.getCustomerType());
        input.put(I_DELEGATE, partnerRequest.getDelegate());
        input.put(I_NOTE, partnerRequest.getNote());
        input.put(I_STATUS, partnerRequest.getStatus());
        input.put(I_IP_PARTNER, partnerRequest.getIpPartner());
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<JsonObject> partnerResponseList = ((List<Map<String, Object>>) map.get(O_PARTNERS))
                .stream().map(stringObjectMap ->
                        new PartnerRequest(stringObjectMap, false).toJson()).collect(Collectors.toList());
        return partnerResponseList;
    }

    @Override
    public List<PartnerRequest> findById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(FIND_BY_ID)
                .declareParameters(
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<PartnerRequest> partnerList = ((List<Map<String, Object>>) map.get(O_PARTNERS))
                .stream().map(stringObjectMap -> new PartnerRequest(stringObjectMap, true)).collect(Collectors.toList());
        return partnerList;
    }

    @Override
    public PartnerRequest getById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(FIND_BY_ID)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<PartnerRequest> partnerList = ((List<Map<String, Object>>) map.get(O_PARTNERS))
                .stream().map(stringObjectMap -> new PartnerRequest(stringObjectMap, true)).collect(Collectors.toList());
        if (partnerList.size() > 0) {
            return partnerList.get(0);
        } else {
            return new PartnerRequest();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PartnerRequest update(PartnerRequest partnerRequest) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(UPDATE_PARTNER)
                .declareParameters(
                        new SqlParameter(I_ID, Types.NUMERIC),
                        new SqlParameter(I_CODE, Types.NVARCHAR),
                        new SqlParameter(I_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARENT_ID, Types.NUMERIC),
                        new SqlParameter(I_SUPPLIER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_CUSTOMER_TYPE, Types.NUMERIC),
                        new SqlParameter(I_DELEGATE, Types.NVARCHAR),
                        new SqlParameter(I_NOTE, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC),
                        new SqlParameter(I_IP_PARTNER, Types.NVARCHAR)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_ID, partnerRequest.getId());
        inParamMap.put(I_CODE, partnerRequest.getCode());
        inParamMap.put(I_NAME, partnerRequest.getName());
        inParamMap.put(I_PARENT_ID, partnerRequest.getPartnerParent());
        inParamMap.put(I_SUPPLIER_TYPE, partnerRequest.getSupplierType());
        inParamMap.put(I_CUSTOMER_TYPE, partnerRequest.getCustomerType());
        inParamMap.put(I_DELEGATE, partnerRequest.getDelegate());
        inParamMap.put(I_NOTE, partnerRequest.getNote());
        inParamMap.put(I_STATUS, partnerRequest.getStatus());
        inParamMap.put(I_IP_PARTNER, partnerRequest.getIpPartner());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAMEIP)
                .withProcedureName(CREATE_PARTNER_IP_ACCESSES)
                .declareParameters(
                        new SqlParameter(I_IP_ACCESS, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_NOTE, Types.NVARCHAR)
                );
        IPAccess ipAccess = partnerRequest.getIpAccesses();
        inParamMap.put(I_IP_ACCESS, ipAccess.getIp());
        inParamMap.put(I_PARTNER_ID, ipAccess.getPartner_id());
        inParamMap.put(I_NOTE, ipAccess.getNote());
        SqlParameterSource in1 = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult1 = simpleJdbcCall.execute(in1);
        return partnerRequest;

    }

    @Override
    public Integer getRecordsTotal() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(COUNTER_LIST)
                .declareParameters(
                        new SqlParameter(I_NAME, Types.NVARCHAR),
                        new SqlParameter(I_PARENT_ID, Types.NUMERIC),
                        new SqlParameter(I_DELEGATE, Types.NVARCHAR),
                        new SqlParameter(I_NOTE, Types.NVARCHAR),
                        new SqlParameter(I_STATUS, Types.NUMERIC)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        Map<String, Object> map = simpleJdbcCall.execute(inParamMap);
        return Integer.valueOf(((List<Map<String, Object>>) map.get(O_PARTNERS)).get(0).get("results").toString());
    }


}
