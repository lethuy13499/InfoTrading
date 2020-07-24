package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartner;
import vn.vmg.infotrading.webapp.internal.sharing_partner.domain.SharingPartnerModel;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SharingPartnerOracleRepository {
    private static final String PKG_SHARING_PARTNERS = "PKG_SHARING_PARTNERS";
    private static final String PRC_GET_ALL_BY_APPENDIX_ID = "PRC_GET_ALL_BY_APPENDIX_ID";
    private static final String PRC_GET_BY_ID = "PRC_GET_BY_ID";
    private static final String PRC_INSERT = "PRC_INSERT";
    private static final String PRC_UPDATE = "PRC_UPDATE";
    private static final String PRC_DELETE = "PRC_DELETE";
    private static final String PRC_DELETE_BY_APPENDIX_ID = "PRC_DELETE_BY_APPENDIX_ID";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    public List<SharingPartner> getAllByAppendixId(Long appendixId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_GET_ALL_BY_APPENDIX_ID)
                .declareParameters(
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_appendix_id", appendixId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<SharingPartner> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj ->
                        new SharingPartner(obj)).collect(Collectors.toList());
        return result;
    }

    public SharingPartner getById(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_GET_BY_ID)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_id", id);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        if (((List<Map<String, Object>>) map.get("o_res")).size() > 0) {
            List<SharingPartner> result = ((List<Map<String, Object>>) map.get("o_res"))
                    .stream().map(obj -> new SharingPartner(obj)).collect(Collectors.toList());
            return result.get(0);
        } else {
            return null;
        }
    }

    public Long insert(SharingPartnerModel sharingPartnerModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_receive_rate", Types.VARCHAR),
                        new SqlParameter("i_note", Types.NVARCHAR),
                        new SqlParameter("i_partner_id", Types.NUMERIC),
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_receive_rate", sharingPartnerModel.getReceiveRate());
        input.put("i_note", sharingPartnerModel.getNote());
        input.put("i_partner_id", sharingPartnerModel.getPartnerId());
        input.put("i_appendix_id", sharingPartnerModel.getAppendixId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    public void update(SharingPartnerModel sharingPartnerModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_UPDATE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC),
                        new SqlParameter("i_receive_rate", Types.VARCHAR),
                        new SqlParameter("i_note", Types.NVARCHAR),
                        new SqlParameter("i_partner_id", Types.NUMERIC),
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", sharingPartnerModel.getId());
        input.put("i_receive_rate", sharingPartnerModel.getReceiveRate());
        input.put("i_note", sharingPartnerModel.getNote());
        input.put("i_partner_id", sharingPartnerModel.getPartnerId());
        input.put("i_appendix_id", sharingPartnerModel.getAppendixId());

        simpleJdbcCall.execute(input);
    }

    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

    public void deleteByAppendixId(Long appendixId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SHARING_PARTNERS)
                .withProcedureName(PRC_DELETE_BY_APPENDIX_ID)
                .declareParameters(
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_appendix_id", appendixId);

        simpleJdbcCall.execute(input);
    }
}
