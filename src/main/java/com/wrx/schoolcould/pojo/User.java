package com.wrx.schoolcould.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String email;
    private String phoneNumber;
    private String studentId;
    private String name;
    private String password;
    private Timestamp lastSigninTime;
    private String lastSigninSite;

    @TableLogic
    private Integer state;
    private Integer usedSpace;
    private Integer availableSpace;

}
