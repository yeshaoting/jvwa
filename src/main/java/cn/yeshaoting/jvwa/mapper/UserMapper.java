package cn.yeshaoting.jvwa.mapper;

import cn.yeshaoting.jvwa.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @description 用户数据库映射
 * 
 * @author yeshaoting
 * @date 2015-08-26 11:41:30
 */
//@Mapper
public interface UserMapper {

    @Insert("insert into jvwa_user(username, stage, create_time, update_time) values(#{user.username}, #{user.stage}, unix_timestamp(), unix_timestamp())")
    @ResultType(java.lang.Integer.class)
    @Options(useCache = true, useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    int insert(@Param("user") User user);
    
    @Insert("update jvwa_user set stage = #{user.stage}, update_time = unix_timestamp() where username = #{user.username}")
    @ResultType(java.lang.Integer.class)
    @Options(useCache = true)
    int update(@Param("user") User user);

    @Select("select id, username, stage, create_time createTime, update_time updateTime from jvwa_user where username = #{username}")
    @ResultType(User.class)
    @Options(useCache = true)
    User findUserByUsername(@Param("username") String username);
}
