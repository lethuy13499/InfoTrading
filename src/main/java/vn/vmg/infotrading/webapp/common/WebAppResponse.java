package vn.vmg.infotrading.webapp.common;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class WebAppResponse {
    private String message;
    private boolean status;
    private Object content;

    public WebAppResponse() {
    }

    public WebAppResponse(JsonObject json) {
        this.message = json.getString("message");
        this.status = json.getBoolean("status");
        this.content = json.getValue("content");
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
