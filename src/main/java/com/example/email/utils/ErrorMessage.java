package com.example.email.utils;

public enum ErrorMessage {
    EmailSendError("发送邮件时出现异常"),
    VerifyCode("验证码错误"),
    ErrorCodeError("出现预料之外的错误");
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
            default:
                return ErrorCodeError;
        }
    }
}
