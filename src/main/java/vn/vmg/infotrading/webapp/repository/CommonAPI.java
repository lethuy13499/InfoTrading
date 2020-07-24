package vn.vmg.infotrading.webapp.repository;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vn.vmg.infotrading.webapp.common.Constants;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.internal.common.domain.CommonModel;
import vn.vmg.infotrading.webapp.internal.parameter.domain.ParameterModel;
import vn.vmg.infotrading.webapp.internal.theme.domain.ThemeModel;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

@Component
public class CommonAPI {

    private final WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public CommonAPI(Vertx vertx) {
        this.webClient = WebClient.create(vertx);
    }

    public JsonArray getParameters(HeaderModel headerModel, ParameterModel parameterModel) {
        HttpHeaders headers = setHttpHeaders(headerModel);
        HttpEntity<ParameterModel> entity = new HttpEntity<>(parameterModel, headers);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/common/get_parameters", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsArr(result);
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject getThemeById(HeaderModel headerModel, ThemeModel themeModel) {
        HttpHeaders headers = setHttpHeaders(headerModel);
        HttpEntity<ThemeModel> entity = new HttpEntity<>(themeModel, headers);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/common/get_theme_of_user", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("data");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject getPasswordProtectedConfig(HeaderModel headerModel, CommonModel commonModel) {
        HttpHeaders headers = setHttpHeaders(headerModel);
        HttpEntity<CommonModel> entity = new HttpEntity<>(commonModel, headers);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/common/get_applied_config_password", HttpMethod.POST, entity, String.class).getBody();

            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    private HttpHeaders setHttpHeaders(HeaderModel headerModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        return httpHeaders;
    }
}
