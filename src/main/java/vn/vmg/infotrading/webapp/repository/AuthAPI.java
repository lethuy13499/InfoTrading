package vn.vmg.infotrading.webapp.repository;

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
import vn.vmg.infotrading.webapp.internal.auth.domain.AuthModel;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

@Component
public class AuthAPI {

    private final WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public AuthAPI(Vertx vertx) {
        this.webClient = WebClient.create(vertx);
    }

    public JsonObject login(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject otpLogin(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login_with_otp", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject totpLogin(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login_with_totp", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject getCode(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login_forget_password", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject checkCode(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login_check_code", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject changePassword(HeaderModel headerModel, AuthModel authModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());

        HttpEntity<AuthModel> entity = new HttpEntity<>(authModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/authentication/login_change_password", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }
}
