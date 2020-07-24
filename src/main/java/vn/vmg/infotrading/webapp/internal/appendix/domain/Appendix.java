package vn.vmg.infotrading.webapp.internal.appendix.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Map;

@DataObject
public class Appendix {
    // Appendix
    private Long id;
    private String code;
    private Integer type;
    private String description;
    private Integer status;
    private Long createTime;
    private Long updateTime;
    private Long signTime;
    private Long validityTime;
    private Long expiryTime;
    private Long contractId;
    private Long partnerId;

    //Other vars
    private String createTimeStr;
    private String updateTimeStr;
    private String signTimeStr;
    private String validityTimeStr;
    private String expiryTimeStr;
    private String contractCode;
    private String partnerName;

    public Appendix() {
    }

    public Appendix(JsonObject json) {
    }

    public Appendix(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.code = StringUtils.isNullOrEmpty(rowData.get("CODE")) ? "" : rowData.get("CODE").toString();
        this.type = StringUtils.isNullOrEmpty(rowData.get("TYPE")) ? null : Integer.valueOf(rowData.get("TYPE").toString());
        this.description = StringUtils.isNullOrEmpty(rowData.get("DESCRIPTION")) ? "" : rowData.get("DESCRIPTION").toString();
        this.status = StringUtils.isNullOrEmpty(rowData.get("STATUS")) ? null : Integer.valueOf(rowData.get("STATUS").toString());
        this.createTime = StringUtils.isNullOrEmpty(rowData.get("CREATETIME")) ? null : Timestamp.valueOf(rowData.get("CREATETIME").toString()).getTime();
        this.createTimeStr = StringUtils.isNullOrEmpty(rowData.get("CREATETIMESTR")) ? null : rowData.get("CREATETIMESTR").toString();
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.updateTimeStr = StringUtils.isNullOrEmpty(rowData.get("UPDATETIMESTR")) ? null : rowData.get("UPDATETIMESTR").toString();
        this.signTime = StringUtils.isNullOrEmpty(rowData.get("SIGNTIME")) ? null : Timestamp.valueOf(rowData.get("SIGNTIME").toString()).getTime();
        this.signTimeStr = StringUtils.isNullOrEmpty(rowData.get("SIGNTIMESTR")) ? null : rowData.get("SIGNTIMESTR").toString();
        this.validityTime = StringUtils.isNullOrEmpty(rowData.get("VALIDITYTIME")) ? null : Timestamp.valueOf(rowData.get("VALIDITYTIME").toString()).getTime();
        this.validityTimeStr = StringUtils.isNullOrEmpty(rowData.get("VALIDITYTIMESTR")) ? null : rowData.get("VALIDITYTIMESTR").toString();
        this.expiryTime = StringUtils.isNullOrEmpty(rowData.get("EXPIRYTIME")) ? null : Timestamp.valueOf(rowData.get("EXPIRYTIME").toString()).getTime();
        this.expiryTimeStr = StringUtils.isNullOrEmpty(rowData.get("EXPIRYTIMESTR")) ? null : rowData.get("EXPIRYTIMESTR").toString();
        this.contractId = StringUtils.isNullOrEmpty(rowData.get("CONTRACTID")) ? null : Long.valueOf(rowData.get("CONTRACTID").toString());
        this.contractCode = StringUtils.isNullOrEmpty(rowData.get("CONTRACTCODE")) ? null : rowData.get("CONTRACTCODE").toString();
        this.partnerId = StringUtils.isNullOrEmpty(rowData.get("PARTNERID")) ? null : Long.valueOf(rowData.get("PARTNERID").toString());
        this.partnerName = StringUtils.isNullOrEmpty(rowData.get("PARTNERNAME")) ? null : rowData.get("PARTNERNAME").toString();
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getSignTime() {
        return signTime;
    }

    public void setSignTime(Long signTime) {
        this.signTime = signTime;
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

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getSignTimeStr() {
        return signTimeStr;
    }

    public void setSignTimeStr(String signTimeStr) {
        this.signTimeStr = signTimeStr;
    }

    public String getValidityTimeStr() {
        return validityTimeStr;
    }

    public void setValidityTimeStr(String validityTimeStr) {
        this.validityTimeStr = validityTimeStr;
    }

    public String getExpiryTimeStr() {
        return expiryTimeStr;
    }

    public void setExpiryTimeStr(String expiryTimeStr) {
        this.expiryTimeStr = expiryTimeStr;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
}
