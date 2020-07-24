package vn.vmg.infotrading.webapp.internal.appendix.repository;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.appendix.domain.Appendix;
import vn.vmg.infotrading.webapp.internal.appendix.domain.AppendixModel;

import java.util.List;

public interface AppendixRepository {

    String PKG_APPENDIXES = "PKG_APPENDIXES";
    String PRC_GET_ALL = "PRC_GET_ALL";
    String PRC_GET_ALL_BY_CONTRACT_ID = "PRC_GET_ALL_BY_CONTRACT_ID";
    String PRC_GET_BY_ID = "PRC_GET_BY_ID";
    String PRC_GET_BY_CODE = "PRC_GET_BY_CODE";
    String PRC_RECORDS_TOTAL = "PRC_RECORDS_TOTAL";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_UPDATE = "PRC_UPDATE";
    String PRC_DELETE = "PRC_DELETE";
    String PRC_DELETE_BY_CONTRACT_ID = "PRC_DELETE_BY_CONTRACT_ID";

    List<JsonObject> getAll(AppendixModel appendixModel);

    List<Appendix> getAllByContractId(Long contractId);

    Appendix getById(Long id);

    Appendix getByCode(String code);

    Integer getRecordsTotal();

    Long insert(AppendixModel appendixModel);

    void update(AppendixModel appendixModel);

    void delete(Long id);

    void deleteByContractId(Long contractId);


}
