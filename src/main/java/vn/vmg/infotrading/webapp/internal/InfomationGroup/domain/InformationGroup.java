package vn.vmg.infotrading.webapp.internal.InfomationGroup.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;

import java.util.Map;

public class InformationGroup {
    private Integer id;
    private String code;
    private String description;
    private Integer status;
    @JsonProperty("Information")
    private Information information;

    public InformationGroup() {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public InformationGroup(JsonObject newInfoGroup) {
        this.id = newInfoGroup.getInteger("id");
        this.code = newInfoGroup.getString("code");
        this.description = newInfoGroup.getString("description");
        this.status = newInfoGroup.getInteger("status");

    }

    public InformationGroup(Map<String, Object> rowData, boolean isInfoGroup) {
        if (isInfoGroup) {
            this.id = Integer.valueOf(rowData.get("ID").toString());
            this.code = rowData.get("CODE").toString();
        }else {
            this.id = Integer.valueOf(rowData.get("ID").toString());
            this.code = rowData.get("CODE").toString();
            this.description = rowData.get("DESCRIPTION").toString();
            this.status = Integer.valueOf(rowData.get("STATUS").toString());
            this.status = Integer.valueOf(rowData.get("STATUS").toString());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}

