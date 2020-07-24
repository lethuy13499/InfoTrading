package vn.vmg.infotrading.webapp.internal.price_setting_detail.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetail;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@DataObject
public class PriceSettingDetail {
    private Long id;
    private Long informationGroupId;
    private String informationField;
    private Integer countMethod;
    private String requireList;
    private Long updateTime;
    private Long priceSettingDetailId;

    // Other vars
    private List<CalculationDetail> calculationDetails;

    public PriceSettingDetail() {
    }

    public PriceSettingDetail(JsonObject json) {
    }

    public PriceSettingDetail(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.informationGroupId = StringUtils.isNullOrEmpty(rowData.get("INFORMATIONGROUPID")) ? null : Long.valueOf(rowData.get("INFORMATIONGROUPID").toString());
        this.informationField = StringUtils.isNullOrEmpty(rowData.get("INFORMATIONFIELD")) ? "" : rowData.get("INFORMATIONFIELD").toString();
        this.countMethod = StringUtils.isNullOrEmpty(rowData.get("COUNTMETHOD")) ? null : Integer.valueOf(rowData.get("COUNTMETHOD").toString());
        this.requireList = StringUtils.isNullOrEmpty(rowData.get("REQUIRELIST")) ? "" : rowData.get("REQUIRELIST").toString();
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.priceSettingDetailId = StringUtils.isNullOrEmpty(rowData.get("PRICESETTINGDETAILID")) ? null : Long.valueOf(rowData.get("PRICESETTINGDETAILID").toString());
//        this.calculationMethod = StringUtils.isNullOrEmpty(rowData.get("CALCULATIONMETHOD")) ? null : Long.valueOf(rowData.get("CALCULATIONMETHOD").toString());
//        this.commitmentInputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("COMMITMENTINPUTQUANTITYFRAME")) ? "" : rowData.get("COMMITMENTINPUTQUANTITYFRAME").toString();
//        this.successOutputQuantityFrame = StringUtils.isNullOrEmpty(rowData.get("SUCCESSOUTPUTQUANTITYFRAME")) ? "" : rowData.get("SUCCESSOUTPUTQUANTITYFRAME").toString();
//        this.unitPrice = StringUtils.isNullOrEmpty(rowData.get("UNITPRICE")) ? null : Double.valueOf(rowData.get("UNITPRICE").toString());
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

    public Integer getCountMethod() {
        return countMethod;
    }

    public void setCountMethod(Integer countMethod) {
        this.countMethod = countMethod;
    }

    public String getRequireList() {
        return requireList;
    }

    public void setRequireList(String requireList) {
        this.requireList = requireList;
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

    public List<CalculationDetail> getCalculationDetails() {
        return calculationDetails;
    }

    public void setCalculationDetails(List<CalculationDetail> calculationDetails) {
        this.calculationDetails = calculationDetails;
    }
}
