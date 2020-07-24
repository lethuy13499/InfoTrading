package vn.vmg.infotrading.webapp.internal.appendix_file.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;

import java.sql.Timestamp;

@DataObject
public class AppendixFileModel extends BaseModel {
    private Long id;
    private String name;
    private String size;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Long appendixId;

    public AppendixFileModel() {
    }

    public AppendixFileModel(JsonObject json) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }
}
