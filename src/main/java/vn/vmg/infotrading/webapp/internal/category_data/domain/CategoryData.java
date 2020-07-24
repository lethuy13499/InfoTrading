package vn.vmg.infotrading.webapp.internal.category_data.domain;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Map;

public class CategoryData {
    private Long id;
    private String code;
    private String name;
    private String json;
    private Integer status;
    private Long createTime;
    private Long updateTime;
    private Long creatorId;
    private Long categoryId;

    public CategoryData() {
    }

    public CategoryData(JsonObject jsonObject) {

    }

    public CategoryData(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.code = StringUtils.isNullOrEmpty(rowData.get("CODE")) ? "" : rowData.get("CODE").toString();
        this.name = StringUtils.isNullOrEmpty(rowData.get("NAME")) ? "" : rowData.get("NAME").toString();
        this.json = StringUtils.isNullOrEmpty(rowData.get("JSON")) ? "" : rowData.get("JSON").toString();
        this.status = StringUtils.isNullOrEmpty(rowData.get("STATUS")) ? null : Integer.valueOf(rowData.get("STATUS").toString());
        this.createTime = StringUtils.isNullOrEmpty(rowData.get("CREATETIME")) ? null : Timestamp.valueOf(rowData.get("CREATETIME").toString()).getTime();
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.creatorId = StringUtils.isNullOrEmpty(rowData.get("CREATORID")) ? null : Long.valueOf(rowData.get("CREATORID").toString());
        this.categoryId = StringUtils.isNullOrEmpty(rowData.get("CATEGORYID")) ? null : Long.valueOf(rowData.get("CATEGORYID").toString());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
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

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
