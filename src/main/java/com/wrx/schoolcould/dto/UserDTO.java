package com.wrx.schoolcould.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wrx.schoolcould.pojo.User;
import lombok.Data;

import java.util.List;


@Data
// 加上此注解后 属性值为 null的 不会进行 json转换传递给前端
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDTO {

    private List<UserBaseDate> baseDates;

    private User user;

    private Integer code;
    private String msg;

    private Integer dateTotal;

}

