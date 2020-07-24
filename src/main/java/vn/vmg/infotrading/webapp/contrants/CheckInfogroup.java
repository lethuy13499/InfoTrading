package vn.vmg.infotrading.webapp.contrants;

import vn.vmg.infotrading.webapp.internal.InternalResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class CheckInfogroup {
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(FORMAT_DATE);
    public static final Integer MAX_FILE_SIZE = 5242880;

    public static Date stringToDate(String val) {
        try {
            return SIMPLE_DATE_FORMAT.parse(val);
        } catch (ParseException e) {
            System.out.println("parse date err:" + e.getStackTrace());
            return null;
        }
    }

    public static String getString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        } else {
            return value;
        }
    }

    public static Object validParamSearch(Object param, String type) {
        switch (type) {
            case "STRING":
                if (param != null && !String.valueOf(param).isEmpty()) {
                    return String.valueOf(param);
                } else {
                    return null;
                }
            case "INT":
                if (param != null && !String.valueOf(param).isEmpty()) {
                    return Integer.parseInt(String.valueOf(param));
                } else {
                    return null;
                }

            default:
                return param;
        }
    }

    public static InternalResult validLengthString(String key, String value, Integer length, String message, InternalResult result) {
        if ((value != null && !value.isEmpty()) && value.length() > length) {
            result.rejectValue(key, key + message + length);
        }
        return result;
    }

    public static InternalResult validObjectExist(String key, Integer value, String message, InternalResult result) {
        if (value == null || value == 0) {
            result.rejectValue(key, key + message);
        }
        return result;
    }

    public static boolean validType(int val, int[] vals) {
        return Arrays.stream(vals).anyMatch(value -> val == value);
    }

    public static boolean validDate(String from, String to) {
        if (CheckInfogroup.getString(from) != null && CheckInfogroup.getString(to) != null) {
            Date outFrom = stringToDate(from);
            Date outTo = stringToDate(to);
            if (outFrom.compareTo(outTo) > 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
