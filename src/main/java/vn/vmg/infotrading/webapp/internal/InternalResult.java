package vn.vmg.infotrading.webapp.internal;

import io.vertx.core.json.JsonObject;
import vn.vmg.infotrading.webapp.config.CommonErrorsMsg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InternalResult {
    private final CommonErrorsMsg commonErrorsMsg;
    private JsonObject errors;
    private boolean status;

    public InternalResult(CommonErrorsMsg commonErrorsMsg) {
        this.errors = new JsonObject();
        this.commonErrorsMsg = commonErrorsMsg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public JsonObject getErrors() {
        return errors;
    }

    public void setErrors(JsonObject errors) {
        this.errors = errors;
    }

    public boolean isSuccess() {
        return status && (errors == null || errors.size() == 0);
    }

    public void rejectIfEmptyOrWhitespace(Object value, String fieldName, String defaultMessage) {
        if (value == null || InternalConstant.EMPTY.equalsIgnoreCase((String) value)) {
            errors.put(fieldName, defaultMessage);
        }
    }

    public void rejectIfEmptyOrWhitespace(String fieldName, Object value) {
        String message = commonErrorsMsg.getEmpty();
        if (value instanceof String) {
            if (value == null || InternalConstant.EMPTY.equalsIgnoreCase((String) value)) {
                errors.put(fieldName, message);
            }
        } else {
            if (value == null) {
                errors.put(fieldName, message);
            }
        }
    }

    public boolean hasErrors() {
        return this.errors != null && this.errors.size() > 0;
    }

    public boolean hasFieldErrors(String fieldName) {
        return errors.containsKey(fieldName);
    }

    public void rejectValue(String fieldName, String message) {
        errors.put(fieldName, message);
    }

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

    public void rejectInvalidValue(String fieldName) {
        this.errors.put(fieldName, fieldName + " is invalid value.");
    }

    public void rejectDuplicateValue(String fieldName) {
        this.errors.put(fieldName, fieldName + " is duplicate value.");
    }
    public void rejectIfRecordChange(String action) {
        this.errors.put(action, "Record changed, please reload and try again");
    }
}
