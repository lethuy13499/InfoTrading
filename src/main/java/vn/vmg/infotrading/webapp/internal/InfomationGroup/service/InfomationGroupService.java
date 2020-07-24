package vn.vmg.infotrading.webapp.internal.InfomationGroup.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroup;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.domain.InformationGroupSearch;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.repository.InfomationGroupRepository;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.validator.DeleteInfoGroupValidator;
import vn.vmg.infotrading.webapp.internal.InfomationGroup.validator.InfomationGroupValidator;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;
import java.util.List;

@Service
public class InfomationGroupService {
    @Autowired
    private InfomationGroupRepository infomationGroupRepository;
    @Autowired
    private InfomationGroupValidator infomationGroupValidator;
    @Autowired
    private DeleteInfoGroupValidator deleteInfoGroupValidator;

    public InternalResult insertInfoGroup(InformationGroup infomationGroup) {
        InternalResult internalResult = infomationGroupValidator.valid(infomationGroup);

        if (!internalResult.hasErrors()) {
            try {

                InformationGroup record = infomationGroupRepository.insertInfoGroup(infomationGroup);
                internalResult.setStatus(true);
            } catch (Exception ex) {
                ex.printStackTrace();
                internalResult.setStatus(false);
            }
        } else {
            internalResult.setStatus(false);
        }
        return internalResult;
    }

    public List<InformationGroup> searchByCode(String code) {
//        InternalResult internalResult = infomationGroupValidator.valid(code);
//        if (!internalResult.hasErrors()) {
//            List<InformationGroup> record = infomationGroupRepository.searchByCode(code);
//            internalResult.setStatus(true);
//        }
        return infomationGroupRepository.searchByCode(code);
    }

    public InternalResult deleteInfoGroup(Integer id) {
            InternalResult internalResult = deleteInfoGroupValidator.valid(id);
            if (internalResult.hasErrors()) {
                internalResult.setStatus(true);
                infomationGroupRepository.deleteInfoGroup(id);
            }
            return internalResult ;
    }

    public List<InformationGroup> searchByDescription(String desciption) {
//        InternalResult internalResult = infomationGroupValidator.valid(desciption);
//        if (!internalResult.hasErrors()) {
//            internalResult.setStatus(true);
//        } else {
//            internalResult.setStatus(false);
//        }
        return infomationGroupRepository.searchByDescription(desciption);
    }

    public List<InformationGroup> searchByStatus(Integer status) {
//        InternalResult internalResult = infomationGroupValidator.valid(status);
//        if (!internalResult.hasErrors()) {
//            internalResult.setStatus(true);
//        } else {
//            internalResult.setStatus(false);
//        }
        return infomationGroupRepository.searchByStatus(status);
    }

    public List<InformationGroup> getById(Integer id) {
//        InternalResult internalResult = infomationGroupValidator.valid(id);
//        if (!internalResult.hasErrors()) {
//            internalResult.setStatus(true);
//        } else {
//            internalResult.setStatus(false);
//        }
        return infomationGroupRepository.getById(id);
    }
    public JsonObject getAll(JsonObject jsonObject) throws IOException{
        InformationGroup informationGroup = JsonUtils.mapJsonObjectToClass(jsonObject.getJsonObject("body"), InformationGroup.class, true);

        JsonObject result = new JsonObject();

        List<JsonObject> inforgroup = infomationGroupRepository.getAll(informationGroup);
        Integer recordsTotal = infomationGroupRepository.getRecordsTotal();

        result.put("data", inforgroup);
        result.put("recordsTotal", recordsTotal);
        result.put("recordsFiltered", inforgroup.size());
        return result;
    }

}

