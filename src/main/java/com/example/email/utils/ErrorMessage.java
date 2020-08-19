package com.example.email.utils;

public enum ErrorMessage {
    EmailSendError("发送邮件时出现异常"),
    VerifyCode("验证码错误"),
    UserNameOrPwdError("用户名或密码错误"),
    ExistUser("注册失败，用户已存在"),
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
            case 1002:
                return UserNameOrPwdError;
            case 1003:
                return ExistUser;
            default:
                return ErrorCodeError;
        }
    }
}
