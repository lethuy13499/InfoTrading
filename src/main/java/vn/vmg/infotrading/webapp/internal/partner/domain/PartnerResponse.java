package vn.vmg.infotrading.webapp.internal.partner.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;

import java.util.List;
import java.util.Map;

@DataObject
public class PartnerResponse {
    private Integer id;
    private String name;
    private String code;
    private String partnerParent;
    private Boolean supplierType;
    private Boolean customerType;
    private String delegate;
    private String note;
    private Integer status;
    private List<IPAccess> ipAccesses;

    public PartnerResponse() {
    }

    public PartnerResponse(JsonObject jsonObject) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public PartnerResponse(Map<String, Object> rowData) {
        this.id = Integer.valueOf(rowData.get("ID").toString());
        this.name = rowData.get("NAME").toString();
        this.code = rowData.get("CODE").toString();
        this.partnerParent = (rowData.get("PARTNER_PARENT") != null) ? rowData.get("PARTNER_PARENT").toString() : "";
        this.customerType= Boolean.valueOf(rowData.get("SUPPLIERTYPE").toString());
        this.customerType = Boolean.valueOf(rowData.get("CUSTOMERTYPE").toString());
        this.delegate = (rowData.get("DELEGATE") != null) ? rowData.get("DELEGATE").toString() : "";
        this.note = (rowData.get("NOTE") != null) ? rowData.get("NOTE").toString() : "";
        this.status = Integer.valueOf(rowData.get("STATUS").toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code;}

    public String getPartnerParent() {
        return partnerParent;
    }

    public void setPartnerParent(String partnerParent) {
        this.partnerParent = partnerParent;
    }

    public Boolean getCustomerType() { return customerType; }

    public void setCustomerType(Boolean customerType) { this.customerType = customerType;}

    public Boolean getSupplierType() { return supplierType; }

    public void setSupplierType(Boolean supplierType) { this.supplierType = supplierType; }

    public String getDelegate() {
        return delegate;
    }

    public void setDelegate(String delegate) {
        this.delegate = delegate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<IPAccess> getIpAccesses() {
        return ipAccesses;
    }

    public void setIpAccesses(List<IPAccess> ipAccesses) {
        this.ipAccesses = ipAccesses;
    }
}
