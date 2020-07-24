package vn.vmg.infotrading.webapp.internal.infomation.repository;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;

import java.util.List;

public interface InformationRepository {
    Information insertInfo(Information info);

    List<Information> getById(Integer id);

    void deleteInfo(Integer id);

    Information updateInfo(Information information);

    List<JsonObject> getAll(Information information);

    Information findById(Integer id);

    Integer getRecordsTotal(Information information);
}
