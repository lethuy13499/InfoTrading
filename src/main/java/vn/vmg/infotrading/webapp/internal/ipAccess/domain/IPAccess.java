package vn.vmg.infotrading.webapp.internal.ipAccess.domain;

import io.vertx.core.json.JsonObject;

import java.util.Map;

public class IPAccess {
    private Integer id;
    private String ip;
    private Integer partner_id;
    private String note;

    public IPAccess() {
    }


    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public IPAccess(JsonObject jsonObject) {

    }

    public IPAccess(Map<String, Object> rowData) {
        this.id = Integer.valueOf(rowData.get("ID").toString());
        this.ip = rowData.get("IP").toString();
        this.partner_id = Integer.valueOf(rowData.get("PARTNER").toString());
        this.note = rowData.get("NOTE").toString();
    }
//    public IPAccess(JsonObject obj) {
//        this.ip = obj.getString("ip");
//        this.partner_id = obj.getInteger("partner_id");
//        this.note = obj.getString("note");
//    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(Integer partner_id) {
        this.partner_id = partner_id;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
