package com.micro.service.galaxycore.dao;

import com.micro.service.galaxycore.pojo.Role;
import com.micro.service.galaxycore.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

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

    @Insert("INSERT INTO user_role(user_id, role_id) VALUES (#{user_id}, #{role_id})")
    void createUserRole(@Param("user_id") long userId, @Param("role_id") long roleId);

    @Select("SELECT u.*, ur.role_id FROM user u\n" +
            "LEFT JOIN user_role ur ON u.id = ur.user_id\n" +
            "WHERE u.username = #{username}")
    @Results(value = {
            @Result(
                    property = "roles", column = "role_id",
                    javaType = List.class, many = @Many(select = "selectRolesByUser")
            )
    })
    User findUserByUsername(String username);

    @Select("select * from role where id = #{id}")
    List<Role> selectRolesByUser(long id);

}
