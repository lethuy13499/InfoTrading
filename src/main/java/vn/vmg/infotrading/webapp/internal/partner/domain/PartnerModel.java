package vn.vmg.infotrading.webapp.internal.partner.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class PartnerModel {
    private long id;
    private String username;
    private double otherInfo;

    public PartnerModel(JsonObject jsonObject) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(double otherInfo) {
        this.otherInfo = otherInfo;
    }
}
