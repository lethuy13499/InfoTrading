package vn.vmg.infotrading.webapp.utils;

import vn.vmg.infotrading.webapp.eventbus.RxResult;

public class ApiResponseUtils {
    private ApiResponseUtils() {
    }

    public static RxResult buildBadRequestResponse(Object content) {
        return buildResponse("Bad request", false, content);
    }

    public static RxResult buildSuccessResponse(String message, Object content) {
        return buildResponse(message, true, content);
    }

    public static RxResult buildFailResponse(String message, Object content) {
        return buildResponse(message, false, content);
    }

    private static RxResult buildResponse(String message, boolean status, Object content) {
        return new RxResult(message, status, content);
    }
}
