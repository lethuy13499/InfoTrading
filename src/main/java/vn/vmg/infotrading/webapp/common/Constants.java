package vn.vmg.infotrading.webapp.common;

public class Constants {

    private Constants() {
    }

    //    public static final String CORE_ADMIN_API_BASE_URL = "http://192.168.18.28:8200/apps/admin_vmg";
    public static final String CORE_ADMIN_API_BASE_URL = "http://localhost:4000/apps/admin_vmg";

    public class ApiResponse {
        public static final String SUCCESS = "SUCCESS";
        public static final String FAIL = "FAIL";
        public static final String ERROR = "ERROR";
    }


//    public static final String CORE_ADMIN_API_BASE_URL = "http://localhost:4000/apps/admin_vmg";

//    public class ApiResponse {
//        public static final String SUCCESS = "SUCCESS";
//        public static final String FAIL = "FAIL";
//        public static final String ERROR = "ERROR";
//    }

    public class LoginType {
        public static final String NORMAL = "NORMAL";
        public static final String OTP = "OTP";
        public static final String TOTP = "TOTP";
    }

    public class Pattern {
        public static final String YYYY_MM_DD = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";
        //         "^[\\\\w-_\\\\.+]*[\\\\w-_\\\\.]\\\\@([\\\\w]+\\\\.)+[\\\\w]+[\\\\w]$";
        public static final String EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        public static final String BASE64 = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";
        public static final String USER_NAME = "^(?=.{1,24}$)(?![_.])[A-Za-z0-9._]+()$";
    }
    public class AppendixType {
        public static final int EXPERIMENT = 1;
        public static final int OFFICIAL = 2;
    }

    public class AppendixStatus {
        public static final int PENDING = 1;
        public static final int APPROVED = 2;
        public static final int DECLINE = 3;
        public static final int CANCEL = 4;
    }

    public class CountMethod {
        public static final int ALL = 1;
        public static final int ANY = 2;
        public static final int REQUIRE_LIST = 3;
    }

    public class CalculationMethod {
        public static final int INPUT_AND_HITRATE = 1;
        public static final int SUCCESS_OUTPUT = 2;
    }

}
