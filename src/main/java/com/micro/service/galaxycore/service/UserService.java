package com.micro.service.galaxycore.service;

import com.micro.service.galaxycore.pojo.User;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 13:38
 * @description
 */
public interface UserService {
    /**
     * @param name
     * @param password
     * @return
     */
    User findUserByUsernamePassword(String name, String password);

    User findUserByUsername(String name);

    /**
     * @param user
     */
    void createUser(User user);
}
