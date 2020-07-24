package vn.vmg.infotrading.webapp.internal.infomation.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.internal.InternalResult;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.infomation.repository.InformationRepository;
import vn.vmg.infotrading.webapp.internal.infomation.validator.InfomationValidator;
import vn.vmg.infotrading.webapp.internal.infomation.validator.InformationDeleteValidator;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

import java.io.IOException;
import java.util.List;

@Service
public class InformationService {
    @Autowired
    private InformationRepository informationRepository;
    @Autowired
    private InfomationValidator infomationValidator;
    @Autowired
    private InformationDeleteValidator informationDeleteValidator;

    public InternalResult insertInfo(Information information) {
        InternalResult internalResult = infomationValidator.valid(information);
        if (!internalResult.hasErrors()) {
            try {
                Information record = informationRepository.insertInfo(information);
                internalResult.setStatus(true);
            } catch (Exception exception) {
                exception.printStackTrace();

            }
        }
        return internalResult;
    }

    public InternalResult deleteInfo(Integer id) {
        InternalResult internalResult = informationDeleteValidator.valid(id);
        if (internalResult.hasErrors()) {
            internalResult.setStatus(true);
        }

        informationRepository.deleteInfo(id);

        return internalResult;

    }

    public List<Information> getById(Integer id) {
        return informationRepository.getById(id);
    }

    public InternalResult updateInfo(Information information) {
        InternalResult internalResult = infomationValidator.valid(information);
        if (internalResult.isSuccess()) {
            internalResult.setStatus(true);
        } else {
            internalResult.getErrors();
        }
        return internalResult;
    }

    public JsonObject getAll(JsonObject jsonObject) throws IOException {

        Information information = JsonUtils.mapJsonObjectToClass(jsonObject.getJsonObject("body"), Information.class, true);

        JsonObject result = new JsonObject();

        List<JsonObject> informationList = informationRepository.getAll(information);
        Integer recordsTotal = informationRepository.getRecordsTotal(information);

        result.put("data", information);
        result.put("recordsTotal", recordsTotal);
        result.put("recordsFiltered", informationList.size());
        return result;
    }

}