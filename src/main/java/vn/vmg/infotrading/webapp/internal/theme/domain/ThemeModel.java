package vn.vmg.infotrading.webapp.internal.theme.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;

@DataObject
public class ThemeModel extends BaseModel {
    private Long id;

    public ThemeModel() {
    }

    public ThemeModel(JsonObject json) {
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
}