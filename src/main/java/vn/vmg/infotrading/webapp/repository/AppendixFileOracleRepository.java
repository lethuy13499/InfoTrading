package vn.vmg.infotrading.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFile;
import vn.vmg.infotrading.webapp.internal.appendix_file.domain.AppendixFileModel;
import vn.vmg.infotrading.webapp.internal.appendix_file.repository.AppendixFileRepository;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class AppendixFileOracleRepository implements AppendixFileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Override
    public List<AppendixFile> getAllByAppendixId(Long appendixId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIX_FILES)
                .withProcedureName(PRC_GET_ALL_BY_APPENDIX_ID)
                .declareParameters(
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("i_appendix_id", appendixId);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        List<AppendixFile> result = ((List<Map<String, Object>>) map.get("o_res"))
                .stream().map(obj -> new AppendixFile(obj)).collect(Collectors.toList());
        return result;
    }

    @Override
    public AppendixFile getById(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIX_FILES)
                .withProcedureName(PRC_GET_BY_ID)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        Map<String, Object> map = simpleJdbcCall.execute(input);
        if (((List<Map<String, Object>>) map.get("o_res")).size() > 0) {
            List<AppendixFile> result = ((List<Map<String, Object>>) map.get("o_res"))
                    .stream().map(obj -> new AppendixFile(obj)).collect(Collectors.toList());
            return result.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Long insert(AppendixFileModel appendixFileModel) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIX_FILES)
                .withProcedureName(PRC_INSERT)
                .declareParameters(
                        new SqlParameter("i_name", Types.NVARCHAR),
                        new SqlParameter("i_size", Types.VARCHAR),
                        new SqlParameter("i_content", Types.VARCHAR),
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_name", appendixFileModel.getName());
        input.put("i_size", appendixFileModel.getSize());
        input.put("i_content", appendixFileModel.getContent());
        input.put("i_appendix_id", appendixFileModel.getAppendixId());

        Map<String, Object> map = simpleJdbcCall.execute(input);
        return Long.valueOf(((List<Map<String, Object>>) map.get("o_res")).get(0).get("id").toString());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIX_FILES)
                .withProcedureName(PRC_DELETE)
                .declareParameters(
                        new SqlParameter("i_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_id", id);

        simpleJdbcCall.execute(input);
    }

    @Override
    public void deleteByAppendixId(Long appendixId) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_APPENDIX_FILES)
                .withProcedureName(PRC_DELETE_BY_APPENDIX_ID)
                .declareParameters(
                        new SqlParameter("i_appendix_id", Types.NUMERIC)
                );
        Map<String, Object> input = new HashMap<>();
        input.put("i_appendix_id", appendixId);

        simpleJdbcCall.execute(input);
    }
}
