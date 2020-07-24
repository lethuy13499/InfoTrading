package vn.vmg.infotrading.webapp.repository;


import io.vertx.core.http.HttpServerRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;
import vn.vmg.infotrading.webapp.internal.ipAccess.repository.IpAccessRepository;
import vn.vmg.infotrading.webapp.internal.ipAccess.service.IPAccessService;
import vn.vmg.infotrading.webapp.internal.partner.service.PartnerService;

import java.io.*;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static vn.vmg.infotrading.webapp.internal.ipAccess.query.IPAccessQuery.*;
import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.I_IP_ACCESS;
import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.I_NOTE;
import static vn.vmg.infotrading.webapp.internal.partner.query.PartnerQuery.I_PARTNER_ID;

@Repository
public class IPAccessOracleRespository implements IpAccessRepository {
    @Lazy
    @Autowired
    private IPAccessService ipAccessService;

    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public IPAccess insertIP(IPAccess ipAccess) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(CREATE_PARTNER_IP_ACCESSES)
                .declareParameters(
                        new SqlParameter(I_IP,Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID,Types.NUMERIC),
                        new SqlParameter(I_NOTE,Types.NVARCHAR)
                );
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put(I_IP,ipAccess.getIp());
        inParamMap.put(I_PARTNER_ID,ipAccess.getPartner_id());
        inParamMap.put(I_NOTE,ipAccess.getNote());
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }
        return ipAccess;
    }
    @Override
    public IPAccess getByIp(String ip) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(GET_BY_IP)
                .declareParameters(new SqlParameter(I_IP, Types.NVARCHAR));
        Map<String, String> input = new HashMap<String, String>();
        input.put(I_IP, ip);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<IPAccess> ipAccesses = ((List<Map<String, Object>>) map.get(O_IP))
                .stream().map(stringObjectMap -> new IPAccess(stringObjectMap)).collect(Collectors.toList());
        if (ipAccesses.size() > 0) {
            return ipAccesses.get(0);

        } else {
            return new IPAccess();
        }
    }

    @Override
    public List<IPAccess> findById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(FIND_BY_ID)
                .declareParameters(new SqlParameter(I_ID, Types.NVARCHAR));
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<IPAccess> ipAccesses = ((List<Map<String, Object>>) map.get(O_IP))
                .stream().map(stringObjectMap -> new IPAccess(stringObjectMap)).collect(Collectors.toList());
        return ipAccesses;
    }

    @Override
    public void deleteIpAccess(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(DELETE_IP_ACCESS)
                .declareParameters(
                       new  SqlParameter(ID_IP_ACCESS,Types.NUMERIC)
                );
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(ID_IP_ACCESS,id);
        Map<String,Object> map = simpleJdbcCall.execute(input);

    }

    @Override
    public IPAccess getById(Integer id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(FIND_BY_ID)
                .declareParameters(new SqlParameter(I_ID, Types.NVARCHAR));
        Map<String, Integer> input = new HashMap<String, Integer>();
        input.put(I_ID, id);
        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<IPAccess> ipAccesses = ((List<Map<String, Object>>) map.get(O_IP))
                .stream().map(stringObjectMap -> new IPAccess(stringObjectMap)).collect(Collectors.toList());
        if (ipAccesses.size() > 0) {
            return ipAccesses.get(0);
        } else {
            return new IPAccess();
        }
    }

    @Override
    public IPAccess update(IPAccess ipAccess) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(CATALOGNAME)
                .withProcedureName(UPDATE_IP_ACCESS)
                .declareParameters(
                        new SqlParameter(I_IP_ACCESS, Types.NVARCHAR),
                        new SqlParameter(I_PARTNER_ID, Types.NUMERIC),
                        new SqlParameter(I_NOTE, Types.NVARCHAR)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put(I_IP_ACCESS, ipAccess.getIp());
        input.put(I_PARTNER_ID, ipAccess.getPartner_id());
        input.put(I_NOTE, ipAccess.getNote());
        SqlParameterSource in1 = new MapSqlParameterSource(input);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in1);
        Iterator<Map.Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            String key = (String) entry.getKey();
            Object value = (Object) entry.getValue();
        }
        return ipAccess;
    }


}

