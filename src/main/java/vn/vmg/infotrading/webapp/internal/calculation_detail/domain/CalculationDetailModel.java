package vn.vmg.infotrading.webapp.internal.calculation_detail.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.sql.Timestamp;

@DataObject
public class CalculationDetailModel {
    private Long id;
    private Integer calculationMethod;
    private String commitmentInputQuantityFrame;
    private String successOutputQuantityFrame;
    private Double unitPrice;
    private Timestamp updateTime;
    private Long priceSettingDetailId;

    public CalculationDetailModel() {
    }

    public CalculationDetailModel(JsonObject json) {
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPriceSettingDetailId() {
        return priceSettingDetailId;
    }

    public void setPriceSettingDetailId(Long priceSettingDetailId) {
        this.priceSettingDetailId = priceSettingDetailId;
    }
}
