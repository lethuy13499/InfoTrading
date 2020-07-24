package vn.vmg.infotrading.webapp.utils;

public class StringUtils {
    private StringUtils() {

    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0 || str.equals("");
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || String.valueOf(obj).length() == 0 || String.valueOf(obj).equals("");
    }
}
