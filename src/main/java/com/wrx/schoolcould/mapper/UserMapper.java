package com.wrx.schoolcould.mapper;

import com.wrx.schoolcould.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where email = #{email}")
    User findByEmail(@Param(value = "email") String email);

}
