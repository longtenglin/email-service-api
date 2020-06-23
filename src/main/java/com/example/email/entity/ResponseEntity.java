package com.example.email.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr.zhaon
 * @date 2020/06/23
 */
@Data
public class ResponseEntity implements Serializable {
    private String code;
    private String msg;
    private Object data;

    public ResponseEntity(){}

    public ResponseEntity(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
