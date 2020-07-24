package vn.vmg.infotrading.webapp.internal.calculation_detail.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Map;

@DataObject
public class CalculationDetail {
    private Long id;
    private Integer calculationMethod;
    private String commitmentInputQuantityFrame;
    private String successOutputQuantityFrame;
    private Double unitPrice;
    private Long updateTime;
    private Long priceSettingDetailId;

    public CalculationDetail() {
    }

    public CalculationDetail(JsonObject json) {
    }

    public CalculationDetail(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.calculationMethod = StringUtils.isNullOrEmpty(rowData.get("CALCULATIONMETHOD")) ? null : Integer.valueOf(rowData.get("CALCULATIONMETHOD").toString());
        this.commitmentInputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("COMMITMENTINPUTQUANTITYFRAME")) ? "" : rowData.get("COMMITMENTINPUTQUANTITYFRAME").toString();
        this.successOutputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("SUCCESSOUTPUTQUANTITYFRAME")) ? "" : rowData.get("SUCCESSOUTPUTQUANTITYFRAME").toString();
        this.unitPrice = StringUtils.isNullOrEmpty(rowData.get("UNITPRICE")) ? null : Double.valueOf(rowData.get("UNITPRICE").toString());
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.priceSettingDetailId = StringUtils.isNullOrEmpty(rowData.get("PRICESETTINGDETAILID")) ? null : Long.valueOf(rowData.get("PRICESETTINGDETAILID").toString());
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

    public Integer getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(Integer calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getCommitmentInputQuantityFrame() {
        return commitmentInputQuantityFrame;
    }

    public void setCommitmentInputQuantityFrame(String commitmentInputQuantityFrame) {
        this.commitmentInputQuantityFrame = commitmentInputQuantityFrame;
    }

    public String getSuccessOutputQuantityFrame() {
        return successOutputQuantityFrame;
    }

    public void setSuccessOutputQuantityFrame(String successOutputQuantityFrame) {
        this.successOutputQuantityFrame = successOutputQuantityFrame;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPriceSettingDetailId() {
        return priceSettingDetailId;
    }

    public void setPriceSettingDetailId(Long priceSettingDetailId) {
        this.priceSettingDetailId = priceSettingDetailId;
    }
}
