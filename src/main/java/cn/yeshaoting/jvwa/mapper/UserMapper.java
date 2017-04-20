package cn.yeshaoting.jvwa.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import cn.yeshaoting.jvwa.entity.User;

/**
 * @description 用户数据库映射
 * 
 * @author yeshaoting
 * @date 2015-08-26 11:41:30
 */
public interface UserMapper {

    @Insert("insert into user(username, stage, create_time, update_time) values(#{user.username}, #{user.stage}, unix_timestamp(), unix_timestamp())")
    @ResultType(java.lang.Integer.class)
    @Options(useCache = true, useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    int insert(@Param("user") User user);
    
    @Insert("update user set stage = #{user.stage}, update_time = unix_timestamp() where username = #{user.username}")
    @ResultType(java.lang.Integer.class)
    @Options(useCache = true)
    int update(@Param("user") User user);

    @Select("select id, username, stage, create_time createTime, update_time updateTime from user where username = #{username}")
    @ResultType(User.class)
    @Options(useCache = true)
    User findUserByUsername(@Param("username") String username);
}
