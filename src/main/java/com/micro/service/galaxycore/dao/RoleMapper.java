package com.micro.service.galaxycore.dao;

import com.micro.service.galaxycore.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 13:29
 * @description
 */
@Mapper
public interface RoleMapper {
    @Select("select * from role where name = #{name}")
    Role findRoleByName(String name);
}
