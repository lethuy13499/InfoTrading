package vn.vmg.infotrading.webapp.internal.sharing_partner.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Map;

@DataObject
public class SharingPartner {
    private Long id;
    private Integer receiveRate;
    private String note;
    private Long updateTime;
    private Long partnerId;
    private Long appendixId;

    public SharingPartner() {
    }

    public SharingPartner(JsonObject json) {
    }

    public SharingPartner(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.receiveRate = StringUtils.isNullOrEmpty(rowData.get("RECEIVERATE")) ? null : Integer.valueOf(rowData.get("RECEIVERATE").toString());
        this.note = StringUtils.isNullOrEmpty(rowData.get("NOTE")) ? null : rowData.get("NOTE").toString();
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.partnerId = StringUtils.isNullOrEmpty(rowData.get("PARTNERID")) ? null : Long.valueOf(rowData.get("PARTNERID").toString());
        this.appendixId = StringUtils.isNullOrEmpty(rowData.get("APPENDIXID")) ? null : Long.valueOf(rowData.get("APPENDIXID").toString());
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

    public Long getUpdateTime() {
        return updateTime ;
    }

    public void setUpdateTime(Long updateTime) {
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
