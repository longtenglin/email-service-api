package com.example.email.utils;

import com.example.email.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UserInfo {

    public static void setUserInfo(HttpServletRequest request, User user){
        request.getSession().setAttribute("user", user);
    }

    public static User getUserInfo(HttpServletRequest request){
        return (User) request.getSession().getAttribute("user");
    }

    public static void setVerifyCode(HttpServletRequest request, String code){
        request.getSession().setAttribute("verifyCode",code);
    }

    public static String getVerifyCode(HttpServletRequest request){
        return (String) request.getSession().getAttribute("verifyCode");
    }
}
