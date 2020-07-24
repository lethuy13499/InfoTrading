package vn.vmg.infotrading.webapp.common;

import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebAppError {
    private final Map<String, Object> errors;

    public WebAppError() {
        this.errors = new HashMap<>();
    }

    public Map<String, Object> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public void rejectIfEmptyOrWhitespace(String fieldName, Object value, String errorMessage) {
        if (value == null || EMPTY.equalsIgnoreCase(value.toString().trim())) {
            this.errors.put(fieldName, errorMessage);
        }
    }

    public void rejectIfEmptyOrWhitespace(String fieldName, Object value) {
        if (value == null || EMPTY.equalsIgnoreCase(value.toString().trim())) {
            this.errors.put(fieldName, fieldName + " is required.");
        }
    }

    public boolean hasFieldErrors(String fieldName) {
        return this.errors.containsKey(fieldName);
    }

    public void rejectValue(String fieldName, String errorMessage) {
        this.errors.put(fieldName, errorMessage);
    }

    public void rejectValue(String fieldName) {
        this.errors.put(fieldName, fieldName + " is invalid value.");
    }

    public JsonObject toJson() {
        JsonObject errorsJson = new JsonObject();
        for (Map.Entry<String, Object> entry : errors.entrySet()) {
            errorsJson.put(entry.getKey(), entry.getValue());
        }
        return errorsJson;
    }

    private static final String EMPTY = "";

    public void rejectIfInvalidMaxLength(String fieldName, String value, int maxLength) {
        if (value.length() > maxLength) {
            this.errors.put(fieldName, "Invalid max length of " + fieldName + ". Max length is " + maxLength);
        }
    }

    public void rejectIfWrongRegex(String fieldName, String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            this.errors.put(fieldName, fieldName + " is invalid regex.");
        }
    }
}
