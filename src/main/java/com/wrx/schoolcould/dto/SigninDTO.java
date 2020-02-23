package com.wrx.schoolcould.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
// 加上此注解后 属性值为 null的 不会进行 json转换传递给前端
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SigninDTO {

    private Integer code;
    private Integer id;
    private String email;
    private String msg;
    private String token;

}
