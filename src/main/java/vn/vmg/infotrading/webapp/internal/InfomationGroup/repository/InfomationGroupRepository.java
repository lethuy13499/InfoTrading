package vn.vmg.infotrading.webapp.internal.InfomationGroup.repository;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroupSearch;

import java.util.List;

public interface InfomationGroupRepository {
    InformationGroup insertInfoGroup(InformationGroup infomationGroup);

    List<InformationGroup> getById(Integer id);

    void deleteInfoGroup(Integer id);

    List<InformationGroup> searchByCode(String code);
    InformationGroup findByCode(String code);

    List<InformationGroup> searchByDescription(String desciption);

    List<InformationGroup> searchByStatus(Integer status);

    InformationGroup updateInfoGroup(InformationGroup infomationGroup);

    Integer searchInfoGroup(InformationGroupSearch search);

    List<JsonObject> getAll(InformationGroup infomationGroup);

    Integer counting(InformationGroupSearch infomationGroupSearch);

    InformationGroup findById(Integer id);

    Integer getRecordsTotal();



}
