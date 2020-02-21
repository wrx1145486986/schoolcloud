package com.wrx.schoolcould.dto;

import lombok.Data;

/*
*       code 说明
*
*       401  账号不存在
*       402  密码错误
*       200  账号密码正确
*
*
* */

@Data
public class SigninDTO {

    private Integer code;
    private Integer id;
    private String email;
    private String msg;
    private String token;

}
