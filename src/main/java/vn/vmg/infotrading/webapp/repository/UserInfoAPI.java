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
import vn.vmg.infotrading.webapp.internal.user_info.domain.UserInfoModel;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

@Component
public class UserInfoAPI {

    private final WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public UserInfoAPI(Vertx vertx) {
        this.webClient = WebClient.create(vertx);
    }

    public JsonObject get(HeaderModel headerModel, UserInfoModel userInfoModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("currentuserid", headerModel.getCurrentuserid().toString());

        HttpEntity<UserInfoModel> entity = new HttpEntity<>(userInfoModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/user_info/get", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject update(HeaderModel headerModel, UserInfoModel userInfoModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());
        httpHeaders.set("currentuserid", headerModel.getCurrentuserid().toString());

        HttpEntity<UserInfoModel> entity = new HttpEntity<>(userInfoModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/user_info/update", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result);
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonArray getMenu(HeaderModel headerModel, UserInfoModel userInfoModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());
        httpHeaders.set("currentuserid", headerModel.getCurrentuserid().toString());
        httpHeaders.set("issuperadmin", headerModel.getIssuperadmin().toString());

        HttpEntity<UserInfoModel> entity = new HttpEntity<>(userInfoModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/user_info/get_menu", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonArray("resData");
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject changePassword(HeaderModel headerModel, UserInfoModel userInfoModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());
        httpHeaders.set("currentuserid", headerModel.getCurrentuserid().toString());

        HttpEntity<UserInfoModel> entity = new HttpEntity<>(userInfoModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/user_info/change_password", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result);
        } catch (Exception ex) {
            return null;
        }
    }

    public JsonObject logout(HeaderModel headerModel, UserInfoModel userInfoModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());
        httpHeaders.set("currentuserid", headerModel.getCurrentuserid().toString());

        HttpEntity<UserInfoModel> entity = new HttpEntity<>(userInfoModel, httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/user_info/log_out", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("resData");
        } catch (Exception ex) {
            return null;
        }
    }
}
