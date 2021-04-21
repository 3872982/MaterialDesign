package com.learning.materialdesign.net;

/**
 * Created by Richen.Xu on 2017/6/5.
 */

public class ApiException extends RuntimeException {

    public static final int USER_NOT_EXIST = 100;
    public static final int WRONG_PASSWORD = 101;
    public static final int NET_ERROR = 102;

    public ApiException(int errorCode) {
        this(getErrorMessage(errorCode));
    }

    public ApiException(String errorMessage) {
        super(errorMessage);
    }

    private static String getErrorMessage(int errorCode) {
        String message = "";
        switch (errorCode) {
            case USER_NOT_EXIST:
                message = "该用户不存在";
                break;
            case WRONG_PASSWORD:
                message = "密码错误";
                break;
            case NET_ERROR:
                message = "网络请求失败";
            default:
                message = "未知错误";

        }
        return message;
    }
}
