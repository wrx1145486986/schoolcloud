package com.wrx.schoolcould.mapper;

import com.wrx.schoolcould.pojo.UserToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserTokenMapper {

    @Insert("insert into userToken values(#{id},#{token})")
    public void insertToken(UserToken userToken);


    @Update("update userToken set token = #{token} where id = #{id}")
    public void updateTokenById(UserToken userToken);

}
