package vn.vmg.infotrading.webapp.internal.gateway.service;

import io.vertx.core.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vmg.infotrading.webapp.internal.HeaderModel;
import vn.vmg.infotrading.webapp.repository.GatewayAPI;

import java.io.IOException;

@Service
public class GatewayService {

    @Autowired
    private GatewayAPI gatewayAPI;

    public JsonObject checkJwt(HeaderModel headerModel) throws IOException {
        JsonObject result = new JsonObject();

        JsonObject jwtInfo = gatewayAPI.checkJwt(headerModel);
        if (jwtInfo == null) {
            result.put("status", false);
            result.put("message", "Jwt is invalid");
        } else {
            result = jwtInfo;
        }

        return result;
    }
}
