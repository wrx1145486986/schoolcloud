package com.wrx.schoolcould.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserBaseDate{
    private Integer id;
    private String email;
    private String phoneNumber;
    private String studentId;
    private String name;
    private String password;
    private String lastSigninSite;
    private Integer availableSpace;
    private Integer state;
}
