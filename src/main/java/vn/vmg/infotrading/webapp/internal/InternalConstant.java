package vn.vmg.infotrading.webapp.internal;

public final class InternalConstant {
    public static final String EMPTY = "";
//    public static final String USERNAME_PATTERN = "^[a-z0-9_]{4,20}$";
    public static final String USERNAME_PATTERN = "^(?=.{1,24}$)(?![_.])[A-Za-z0-9._]+()$";
    public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
//    public static final String PHONE_PATTERN = "^[0-9]{10}$";
    public static final String PHONE_PATTERN = "^(?=.{1,20}$)([+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*)$";
    public static final String IDENTITY_CARD_PATTERN = "^[0-9]{9,12}$";
    public static final String CODE_PRODUCT_PATTERN = "^[a-zA-Z0-9]{4,30}$";
    public static final String NUMBER_PATTERN = "^[0-9]$";
    public static final String IP_PATTERN_IPV4 = "^((0|1\\\\d?\\\\d?|2[0-4]?\\\\d?|25[0-5]?|[3-9]\\\\d?)\\\\.){3}(0|1\\\\d?\\\\d?|2[0-4]?\\\\d?|25[0-5]?|[3-9]\\\\d?)$";
    public static final String CODE_PATTNER = "";
    public static final String NAME_PARTNER = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    public static final String TYPE_PARTNER = "/^(0|1)$/";
    public static final String IP_PARTNER_IPV6 = "/^([0-9a-f]){1,4}(:([0-9a-f]){1,4}){7}$/i";
    public static final String IP_PATTERN = "([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4}|(\\d{1,3}\\.){3}\\d{1,3}";

    public static final String EMAIL_PATTERN = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    public static final String YYYY_MM_DD_PATTERN = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
}
