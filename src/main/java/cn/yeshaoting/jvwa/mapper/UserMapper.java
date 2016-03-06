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

    @Insert("replace into user(username, stage) values(#{user.username}, #{user.stage})")
    @ResultType(java.lang.Integer.class)
    @Options(useCache = true, useGeneratedKeys = true, keyProperty = "user.id", keyColumn = "id")
    int replace(@Param("user") User user);

    @Select("select * from user where username = #{username}")
    @ResultType(User.class)
    @Options(useCache = true)
    User findUserByUsername(@Param("username") String username);
}
