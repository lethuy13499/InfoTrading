package vn.vmg.infotrading.webapp.internal.InfomationGroup.domain;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.internal.SearchCommon;

import java.util.Map;


public class InformationGroupSearch extends SearchCommon {
    private String code;
    private String description;
    private Integer status;


    public InformationGroupSearch() {

    }

    public InformationGroupSearch(JsonObject params, int type) {
        String[] fitlers = params.getString("search[value]").split("\\|", -1);
        if (fitlers.length > 0) {
            this.code = (getString(fitlers[0]));
            this.description = (getString(fitlers[1]));
            this.status = (!(fitlers[1]).isEmpty()) ? Integer.valueOf(getString(fitlers[1])) : null;
        } else {
            this.setOrderBy("created_at");
        }
        if (type == 0) {
            this.setOrderType(params.getString("order[0][dir]"));
            this.setSkip((!params.getString("start").isEmpty() || params.getString("start") == null)
                    ? Integer.valueOf(params.getString("start")) : 0);
            this.setTake((!params.getString("length").isEmpty() || params.getString("length") == null)
                    ? Integer.valueOf(params.getString("length")) : 10);

        }
    }
    public InformationGroupSearch(Map<String, Object> stringObjectMap, boolean isInfoGroup) {
        if (isInfoGroup) {
            this.code = stringObjectMap.get("CODE").toString();
            this.description = stringObjectMap.get("DESCRIPTION").toString();
        } else {
            this.code = stringObjectMap.get("CODE").toString();
            this.description = stringObjectMap.get("DESCRIPTION").toString();
            this.status = Integer.valueOf(stringObjectMap.get("STATUS").toString());
        }
    }
    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private String getString(String value) {
        if (value.isEmpty() || value == null) {
            return null;
        } else {
            return value;
        }
    }
}
