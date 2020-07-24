package vn.vmg.infotrading.webapp.internal.sharing_partner.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;

import java.sql.Timestamp;

@DataObject
public class SharingPartnerModel extends BaseModel {
    private Long id;
    private Integer receiveRate;
    private String note;
    private Timestamp updateTime;
    private Long partnerId;
    private Long appendixId;

    public SharingPartnerModel() {
    }

    public SharingPartnerModel(JsonObject json) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(Integer receiveRate) {
        this.receiveRate = receiveRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }
}
