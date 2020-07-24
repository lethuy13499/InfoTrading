package vn.vmg.infotrading.webapp.internal.sample.domain;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

import java.util.Date;

@DataObject
public class CustomerModel {
    private int id;
    private String name;
    private String username;
    private String email;
    private Date date;

    public CustomerModel(JsonObject jsonObject) {
        this.id = jsonObject.getInteger("id");
        this.name = jsonObject.getString("name");
        this.username = jsonObject.getString("username");
//        this.date = new Date(jsonObject.getLong("date"));
        this.email = jsonObject.getString("email");
    }

    public JsonObject toJson() {
        return JsonObject.mapFrom(this);
    }

    public CustomerModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CustomerModel(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
