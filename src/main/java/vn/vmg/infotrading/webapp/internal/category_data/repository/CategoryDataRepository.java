package vn.vmg.infotrading.webapp.internal.category_data.repository;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryData;
import vn.vmg.infotrading.webapp.internal.category_data.domain.CategoryDataModel;

import java.util.List;

public interface CategoryDataRepository {

    String PKG_CATEGORY_DATAS = "PKG_CATEGORY_DATAS";
    String PRC_GET_ALL = "PRC_GET_ALL";
    String PRC_GET_BY_ID = "PRC_GET_BY_ID";
    String PRC_RECORDS_TOTAL = "PRC_RECORDS_TOTAL";
    String PRC_INSERT = "PRC_INSERT";
    String PRC_UPDATE = "PRC_UPDATE";
    String PRC_DELETE = "PRC_DELETE";

    List<JsonObject> getAll(CategoryDataModel categoryDataModel);

    CategoryData getById(Long id);

    Integer getRecordsTotal(Long categoryId);

    Long insert(CategoryDataModel categoryDataModel);

    void update(CategoryDataModel categoryDataModel);

    void delete(Long id);

}
