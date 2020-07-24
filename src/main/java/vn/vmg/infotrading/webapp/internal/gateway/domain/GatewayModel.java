package vn.vmg.infotrading.webapp.internal.gateway.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class GatewayModel {

    public GatewayModel() {
    }

    public GatewayModel(JsonObject json) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }


}
