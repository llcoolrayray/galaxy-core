package com.micro.service.galaxycore.dao;

import com.micro.service.galaxycore.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 13:29
 * @description
 */
@Mapper
public interface UserMapper {
    @Select("select * from User where username = #{username} and password = #{password}")
    User findByUsernamePassword(String username, String password);

    @Insert("INSERT INTO user(username, password, salt, email, phone) VALUES(#{username}, #{password}, #{salt}, #{email}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createUser(User user);

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Insert("INSERT INTO user_role(user_id, role_id) VALUES (#{user_id}, #{role_id})")
    void createUserRole(@Param("user_id") long userId, @Param("role_id") long roleId);


}
