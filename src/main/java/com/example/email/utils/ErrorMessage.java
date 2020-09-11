package com.example.email.utils;

public enum ErrorMessage {
    EmailSendError("An exception occurred while sending the message"),
    VerifyCode("verification code error"),
    UserNameOrPwdError("username or password error"),
    ExistUser("Registration failed, username already exists"),
    ErrorCodeError("Unexpected error"),
    CreateTokenFail("Token obtained failed"),
    CheckTokenInvalid("Invalid Token"),
    CheckTokenOverTime("Token has timed out"),
    CheckTokenNull("Token is null");

    private String message;
    public String getValue(){
        return message;
    }
    ErrorMessage(String message){
        this.message = message;
    }

    public static ErrorMessage valueOf(int value) {
        switch (value) {
            case 1000:
                return EmailSendError;
            case 1001:
                return VerifyCode;
            case 1002:
                return UserNameOrPwdError;
            case 1003:
                return ExistUser;
            case 2000:
                return CreateTokenFail;
            case 2001:
                return CheckTokenInvalid;
            case 2002:
                return CheckTokenOverTime;
            case 2003:
                return CheckTokenNull;
            default:
                return ErrorCodeError;
        }
    }
}
