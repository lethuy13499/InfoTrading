package vn.vmg.infotrading.webapp.internal.partner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import vn.vmg.infotrading.webapp.internal.infomation.domain.Information;
import vn.vmg.infotrading.webapp.internal.ipAccess.domain.IPAccess;

import java.util.Map;


@DataObject
public class PartnerRequest extends JsonObject {
    private Integer id;
    private String code;
    private String name;
    private String partnerParent;
    private Integer supplierType;
    private Integer customerType;
    private String delegate;
    private String note;
    private Integer status;
    private String ipPartner;
    @JsonProperty("IPAccess")
    private IPAccess ipAccesses;
    @JsonProperty("Information")
    private Information information;

    public PartnerRequest() {
    }


    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }


    public PartnerRequest(JsonObject newPartner) {
        this.id = newPartner.getInteger("id");
        this.code = newPartner.getString("code");
        this.name = newPartner.getString("name");
        this.partnerParent = newPartner.getString("partnerParent");
        this.supplierType = newPartner.getInteger("supplierType");
        this.customerType = newPartner.getInteger("customerType");
        this.delegate = newPartner.getString("delegate");
        this.note = newPartner.getString("note");
        this.status = newPartner.getInteger("status");
        this.ipPartner = newPartner.getString("ipPartner");
    }

    public PartnerRequest(Map<String, Object> rowData, boolean isParent) {
        if (isParent) {
            this.id = Integer.valueOf(rowData.get("ID").toString());
            this.name = rowData.get("NAME").toString();
        } else {
            this.id = Integer.valueOf(rowData.get("ID").toString());
            this.code = rowData.get("CODE").toString();
            this.name = rowData.get("NAME").toString();
            this.partnerParent = (rowData.get("PARTNER_PARENT") != null) ? rowData.get("PARTNER_PARENT").toString() : "";
            this.supplierType = Integer.valueOf(rowData.get("SUPPLIERTYPE").toString());
            this.customerType = Integer.valueOf(rowData.get("CUSTOMERTYPE").toString());
            this.delegate = (rowData.get("DELEGATE") != null) ? rowData.get("DELEGATE").toString() : "";
            this.note = (rowData.get("NOTE") != null) ? rowData.get("NOTE").toString() : "";
            this.status = Integer.valueOf(rowData.get("STATUS").toString());
            this.ipPartner = (rowData.get("IP_PARTNER") != null) ? rowData.get("IP_PARTNER").toString():"";

        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnerParent() {
        return partnerParent;
    }

    public void setPartnerParent(String partnerParent) {
        this.partnerParent = partnerParent;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

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


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getIpPartner() {
        return ipPartner;
    }

    public void setIpPartner(String ipPartner) {
        this.ipPartner = ipPartner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IPAccess getIpAccesses() {
        return ipAccesses;
    }

    public void setIpAccesses(IPAccess ipAccesses) {
        this.ipAccesses = ipAccesses;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
