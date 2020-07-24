package vn.vmg.infotrading.webapp.eventbus;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class RxResult {
    private boolean status;
    private String message;
    private Object content;

    public RxResult() {
    }

    public RxResult(JsonObject payload) {
        this.status = payload.getBoolean("status");
        this.message = payload.getString("message");
        this.content = payload.getValue("content");
    }

    public RxResult(String message, boolean status, Object content) {
        this.message = message;
        this.status = status;
        this.content = content;
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
