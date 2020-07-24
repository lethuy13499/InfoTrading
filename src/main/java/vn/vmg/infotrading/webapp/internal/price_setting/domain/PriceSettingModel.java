package vn.vmg.infotrading.webapp.internal.price_setting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;
import vn.vmg.infotrading.webapp.internal.price_setting_detail.domain.PriceSettingDetailModel;

import java.sql.Timestamp;

@DataObject
public class PriceSettingModel extends BaseModel {
    // PriceSetting
    private Long id;
    private Timestamp updateTime;
    private Timestamp validityTime;
    private Timestamp expiryTime;
    private Long appendixId;

    // Other vars
    @JsonProperty("priceSettingDetail")
    private PriceSettingDetailModel priceSettingDetailModel;

    public PriceSettingModel() {
    }

    public PriceSettingModel(JsonObject json) {
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Timestamp validityTime) {
        this.validityTime = validityTime;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public Long getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }

    public PriceSettingDetailModel getPriceSettingDetailModel() {
        return priceSettingDetailModel;
    }

    public void setPriceSettingDetailModel(PriceSettingDetailModel priceSettingDetailModel) {
        this.priceSettingDetailModel = priceSettingDetailModel;
    }
}
