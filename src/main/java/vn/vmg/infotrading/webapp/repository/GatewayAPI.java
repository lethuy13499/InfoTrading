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
import vn.vmg.infotrading.webapp.internal.gateway.domain.GatewayModel;
import vn.vmg.infotrading.webapp.utils.JsonUtils;

@Component
public class GatewayAPI {

    private final WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public GatewayAPI(Vertx vertx) {
        this.webClient = WebClient.create(vertx);
    }

    public JsonObject checkJwt(HeaderModel headerModel) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Accept-Language", headerModel.getAcceptlanguage());
        httpHeaders.set("portalid", headerModel.getPortalid().toString());
        httpHeaders.set("Authorization", headerModel.getAuthorization());

        HttpEntity<GatewayModel> entity = new HttpEntity<>(new GatewayModel(), httpHeaders);

        try {
            String result = restTemplate.exchange(
                    Constants.CORE_ADMIN_API_BASE_URL + "/gateway/check_jwt", HttpMethod.POST, entity, String.class).getBody();
            return JsonUtils.strToJsObj(result).getJsonObject("data");
        } catch (Exception ex) {
            return null;
        }
    }
}
