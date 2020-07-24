package vn.vmg.infotrading.webapp.internal.parameter.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.BaseModel;

@DataObject
public class ParameterModel extends BaseModel {
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

    public ParameterModel() {
    }

    public ParameterModel(JsonObject json) {
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

}