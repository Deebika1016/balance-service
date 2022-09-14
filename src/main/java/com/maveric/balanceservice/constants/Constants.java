package com.maveric.balanceservice.constants;

import java.time.LocalDateTime;
public class Constants {

    private Constants()
    {

    }
    public static LocalDateTime getCurrentDateTime() {
        return (java.time.LocalDateTime.now());
    }
    public static final String BALANCE_NOT_FOUND_CODE="404";
    public static final String BALANCE_NOT_FOUND_MESSAGE="Balance not Found for Id-";
    public static final String BALANCE_DELETED_SUCCESS="Balance deleted successfully.";
    public static final String METHOD_NOT_ALLOWED_CODE="405";
    public static final String METHOD_NOT_ALLOWED_MESSAGE="Method Not Allowed. Kindly check the Request URL and Request Type.";
    public static final String BAD_REQUEST_CODE="400";
    public static final String BAD_REQUEST_MESSAGE="Invalid inputs!";

    public static final String INVALID_INPUT_TYPE="Currency should be INR/EURO/DOLLAR";
    public static final String HttpMessageNotReadableException_MESSAGE="Format Miss Matching";
}

