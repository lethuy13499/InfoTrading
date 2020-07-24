package vn.vmg.infotrading.webapp.internal.group.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class GroupModel {

    public GroupModel() {
    }

    public GroupModel(JsonObject json) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }


}
