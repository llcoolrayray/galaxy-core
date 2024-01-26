package com.micro.service.galaxycore.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 13:30
 * @description
 */
@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private int phone;

    /**
     * 使用组合盐值
     * 将用户名和盐值组合使用，是一种提高安全性的策略。这意味着盐值不仅是随机的，而且还因用户而异。
     * 即使两个不同的用户碰巧有相同的盐值，它们的最终盐值（用户名+盐值）也将是不同的。
     *
     * @return
     */
    public String getCredentialsSalt() {
        return username + salt;
    }
}
