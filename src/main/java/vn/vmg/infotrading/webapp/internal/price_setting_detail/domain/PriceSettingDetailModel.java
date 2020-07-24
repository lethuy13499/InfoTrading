package vn.vmg.infotrading.webapp.internal.price_setting_detail.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;
import vn.vmg.infotrading.webapp.internal.calculation_detail.domain.CalculationDetailModel;

import java.sql.Timestamp;
import java.util.List;

@DataObject
public class PriceSettingDetailModel extends BaseModel {
    private Long id;
    private Long informationGroupId;
    private String informationField;
    private Integer countMethod;
    private String requireList;
    private Timestamp updateTime;
    private Long priceSettingId;

    @JsonProperty("calculationDetails")
    private List<CalculationDetailModel> calculationDetailModels;

    public PriceSettingDetailModel() {
    }

    public PriceSettingDetailModel(JsonObject json) {
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getPriceSettingId() {
        return priceSettingId;
    }

    public void setPriceSettingId(Long priceSettingId) {
        this.priceSettingId = priceSettingId;
    }

    public List<CalculationDetailModel> getCalculationDetailModels() {
        return calculationDetailModels;
    }

    public void setCalculationDetailModels(List<CalculationDetailModel> calculationDetailModels) {
        this.calculationDetailModels = calculationDetailModels;
    }
}
