package vn.vmg.infotrading.webapp.internal.infomation.domain;

import io.vertx.core.json.JsonObject;

import java.util.Map;

public class Information {
    private Integer id;
    private String fieldName;
    private Integer partnerId;
    private Integer inforGroupId;



    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public Information(JsonObject jsonObject) {

    }

    public Information() {
    }

    public Information(Map<String, Object> rowData, boolean isInfo) {
        if (isInfo) {
            this.id = Integer.valueOf(rowData.get("ID").toString());
        } else {
            this.id = Integer.valueOf(rowData.get("ID").toString());
            this.fieldName = rowData.get("FIELD_NAME").toString();
            this.inforGroupId = Integer.valueOf(rowData.get("INFO_GROUP_ID").toString());
            this.partnerId = Integer.valueOf(rowData.get("PARTNER_ID").toString());
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getInforGroupId() {
        return inforGroupId;
    }

    public void setInforGroupId(Integer inforGroupId) {
        this.inforGroupId = inforGroupId;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }
}
