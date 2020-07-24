package vn.vmg.infotrading.webapp.internal.common.service;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.eventbus.RxResult;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.common.domain.CommonModel;
import vn.vmg.infotrading.webapp.internal.parameter.domain.ParameterModel;
import vn.vmg.infotrading.webapp.internal.theme.domain.ThemeModel;
import vn.vmg.infotrading.webapp.repository.CommonAPI;
import vn.vmg.infotrading.webapp.utils.ApiResponseUtils;

import java.io.IOException;

@Service
public class CommonService {

    @Autowired
    private CommonAPI commonAPI;

    public RxResult getParameters(HeaderModel headerModel, ParameterModel parameterModel) throws IOException {
        JsonArray result = commonAPI.getParameters(headerModel, parameterModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Get parameters fail", null);
        }

        return ApiResponseUtils.buildSuccessResponse("Get parameters successful", result);
    }

    public RxResult getThemeById(HeaderModel headerModel, ThemeModel themeModel) throws IOException {
        JsonObject result = commonAPI.getThemeById(headerModel, themeModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Get theme by id fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Get theme by id successful", result);
    }

    public RxResult getPasswordProtectedConfig(HeaderModel headerModel, CommonModel commonModel) throws IOException {
        JsonObject result = commonAPI.getPasswordProtectedConfig(headerModel, commonModel);
        if (result == null) {
            return ApiResponseUtils.buildFailResponse("Get password protected config fail", result);
        }

        return ApiResponseUtils.buildSuccessResponse("Get password protected config successful", result);
    }
}