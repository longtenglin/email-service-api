package com.example.email.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Declaration: 封装返回信息
 * @Author: Mr.zhao_nan
 */
public class ResponseMap {

    public static Map<String,Object> sendMessage(String msg){
        Map<String, Object> map = new HashMap<>();
        map.put("resCode", 0);
        map.put("msg", msg);
        map.put("data", "");
        return map;
    }

    public static Map<String,Object> sendMessage(String msg, Object object){
        Map<String, Object> map = new HashMap<>();
        map.put("resCode", 0);
        map.put("msg", msg);
        map.put("data", object);
        return map;
    }
}
