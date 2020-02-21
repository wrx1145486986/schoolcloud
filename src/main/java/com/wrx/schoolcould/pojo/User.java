package com.wrx.schoolcould.pojo;

import lombok.Data;

import java.security.Timestamp;

@Data
public class User {

    private Integer id;
    private String email;
    private String phoneNumber;
    private String studentId;
    private String uname;
    private String upwd;
    private Timestamp lastLoginTime;
    private String lastLoginSite;
    private Integer status;
    private Double usedSpace;
    private Double available_space;

}
