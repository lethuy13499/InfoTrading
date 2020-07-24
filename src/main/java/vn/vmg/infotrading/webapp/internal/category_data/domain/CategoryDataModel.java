package vn.vmg.infotrading.webapp.internal.category_data.domain;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;

import java.sql.Timestamp;

public class CategoryDataModel extends BaseModel {
  // Category Data
  private Long id;
  private String code;
  private String name;
  private String json;
  private Integer status;
  private Timestamp createTime;
  private Timestamp updateTime;
  private Long creatorId;
  private Long categoryId;

  // Other vars
  private String commonSearch;

  public CategoryDataModel() {
  }

  public CategoryDataModel(JsonObject jsonObject) {

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

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Timestamp updateTime) {
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

  public String getCommonSearch() {
    return commonSearch;
  }

  public void setCommonSearch(String commonSearch) {
    this.commonSearch = commonSearch;
  }
}
