package com.wrx.schoolcould.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wrx.schoolcould.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {


    /*
    * 查询逻辑删除时使用mybatis自带注解扫描
    *
    * */

    @Select("select * from user limit #{currentPage},#{end}")
    List<User> list(@Param("currentPage") Integer currentPage,@Param("end") Integer end);

    /**
     * 查询用户总数（含逻辑删除）
     * @return
     */
    @Select("select count(1) from user")
    Integer selectCount();

    /**
     * 根据用户id号修改账号状态
     * @param userID
     * @param state
     * @return
     */
    @Update("update user set state = #{state} where id = #{userID}")
    Integer updateUserStateById(@Param("userID") Integer userID,@Param("state") Integer state);

    /**
     * 根据id查询用户
     */
    @Select("select * from user where id = #{id}")
    User selectById(@Param(value = "id") Integer id);

    @Select("select email,state from user where id = #{id}")
    User selectUserStateById(@Param(value = "id")Integer userID);
}
