package vn.vmg.infotrading.webapp.internal.price_setting.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetail;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@DataObject
public class PriceSetting {
    // PriceSetting
    private Long id;
    private Long updateTime;
    private Long validityTime;
    private Long expiryTime;
    private Long priceSettingDetailId;
    private Long appendixId;

    // Other vars
    private Long informationGroupId;
    private String informationField;
    private Long countMethod;
    private String requireList;
    private Long calculationMethod;
    private String commitmentInputQuantityFrame;
    private String successOutputQuantityFrame;
    private Double unitPrice;
    private List<PriceSettingDetail> priceSettingDetails;

    public PriceSetting() {
    }

    public PriceSetting(JsonObject json) {
    }

    public PriceSetting(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.validityTime = StringUtils.isNullOrEmpty(rowData.get("VALIDITYTIME")) ? null : Timestamp.valueOf(rowData.get("VALIDITYTIME").toString()).getTime();
        this.expiryTime = StringUtils.isNullOrEmpty(rowData.get("EXPIRYTIME")) ? null : Timestamp.valueOf(rowData.get("EXPIRYTIME").toString()).getTime();
        this.priceSettingDetailId = StringUtils.isNullOrEmpty(rowData.get("PRICESETTINGDETAILID")) ? null : Long.valueOf(rowData.get("PRICESETTINGDETAILID").toString());
        this.appendixId = StringUtils.isNullOrEmpty(rowData.get("APPENDIXID")) ? null : Long.valueOf(rowData.get("APPENDIXID").toString());

        this.informationGroupId = StringUtils.isNullOrEmpty(rowData.get("INFORMATIONGROUPID")) ? null : Long.valueOf(rowData.get("INFORMATIONGROUPID").toString());
        this.informationField = StringUtils.isNullOrEmpty(rowData.get("INFORMATIONFIELD")) ? "" : rowData.get("INFORMATIONFIELD").toString();
        this.countMethod = StringUtils.isNullOrEmpty(rowData.get("COUNTMETHOD")) ? null : Long.valueOf(rowData.get("COUNTMETHOD").toString());
        this.requireList = StringUtils.isNullOrEmpty(rowData.get("REQUIRELIST")) ? "" : rowData.get("REQUIRELIST").toString();
        this.calculationMethod = StringUtils.isNullOrEmpty(rowData.get("CALCULATIONMETHOD")) ? null : Long.valueOf(rowData.get("CALCULATIONMETHOD").toString());
        this.commitmentInputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("COMMITMENTINPUTQUANTITYFRAME")) ? "" : rowData.get("COMMITMENTINPUTQUANTITYFRAME").toString();
        this.successOutputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("SUCCESSOUTPUTQUANTITYFRAME")) ? "" : rowData.get("SUCCESSOUTPUTQUANTITYFRAME").toString();
        this.unitPrice = StringUtils.isNullOrEmpty(rowData.get("UNITPRICE")) ? null : Double.valueOf(rowData.get("UNITPRICE").toString());
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Long validityTime) {
        this.validityTime = validityTime;
    }

    public Long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getPriceSettingDetailId() {
        return priceSettingDetailId;
    }

    public void setPriceSettingDetailId(Long priceSettingDetailId) {
        this.priceSettingDetailId = priceSettingDetailId;
    }

    public Long getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }

    public Long getInformationGroupId() {
        return informationGroupId;
    }

    public void setInformationGroupId(Long informationGroupId) {
        this.informationGroupId = informationGroupId;
    }

    public String getInformationField() {
        return informationField;
    }

    public void setInformationField(String informationField) {
        this.informationField = informationField;
    }

    public Long getCountMethod() {
        return countMethod;
    }

    public void setCountMethod(Long countMethod) {
        this.countMethod = countMethod;
    }

    public String getRequireList() {
        return requireList;
    }

    public void setRequireList(String requireList) {
        this.requireList = requireList;
    }

    public Long getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(Long calculationMethod) {
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

    public List<PriceSettingDetail> getPriceSettingDetails() {
        return priceSettingDetails;
    }

    public void setPriceSettingDetails(List<PriceSettingDetail> priceSettingDetails) {
        this.priceSettingDetails = priceSettingDetails;
    }
}
