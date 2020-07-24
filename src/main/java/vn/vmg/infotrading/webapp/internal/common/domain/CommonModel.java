package vn.vmg.infotrading.webapp.internal.common.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class CommonModel {
//    private Long id;
//    private String name;
//    private String username;
//    private String groupIds;
//    private Long isPartner;
//    private Long status;
//    private String orderBy;
//    private String orderType;
//    private Long skip;
//    private Long take;

    public CommonModel() {
    }

    public CommonModel(JsonObject json) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

}
