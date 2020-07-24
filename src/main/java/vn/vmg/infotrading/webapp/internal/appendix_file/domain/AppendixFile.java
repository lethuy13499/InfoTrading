package vn.vmg.infotrading.webapp.internal.appendix_file.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.utils.StringUtils;

import java.sql.Timestamp;
import java.util.Map;

@DataObject
public class AppendixFile {
    private Long id;
    private String name;
    private String size;
    private String content;
    private Long createTime;
    private Long updateTime;
    private Long appendixId;

    public AppendixFile() {
    }

    public AppendixFile(JsonObject json) {
    }

    public AppendixFile(Map<String, Object> rowData) {
        this.id = StringUtils.isNullOrEmpty(rowData.get("ID")) ? null : Long.valueOf(rowData.get("ID").toString());
        this.name = StringUtils.isNullOrEmpty(rowData.get("NAME")) ? "" : rowData.get("NAME").toString();
        this.size = StringUtils.isNullOrEmpty(rowData.get("SIZE")) ? "" : rowData.get("SIZE").toString();
        this.content = StringUtils.isNullOrEmpty(rowData.get("CONTENT")) ? "" : rowData.get("CONTENT").toString();
        this.createTime = StringUtils.isNullOrEmpty(rowData.get("CREATETIME")) ? null : Timestamp.valueOf(rowData.get("CREATETIME").toString()).getTime();
        this.updateTime = StringUtils.isNullOrEmpty(rowData.get("UPDATETIME")) ? null : Timestamp.valueOf(rowData.get("UPDATETIME").toString()).getTime();
        this.appendixId = StringUtils.isNullOrEmpty(rowData.get("APPENDIXID")) ? null : Long.valueOf(rowData.get("APPENDIXID").toString());
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

    public Long getAppendixId() {
        return appendixId;
    }

    public void setAppendixId(Long appendixId) {
        this.appendixId = appendixId;
    }
}
