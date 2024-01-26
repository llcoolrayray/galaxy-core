package com.micro.service.galaxycore.controller;

import com.micro.service.galaxycore.common.ServerResponse;
import com.micro.service.galaxycore.pojo.User;
import com.micro.service.galaxycore.service.UserService;
import com.micro.service.galaxycore.util.PasswordHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author ray
 * @date 2019/8/17 13:13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordHelper passwordHelper;

    @PostMapping("/signup")
    public ServerResponse<?> getUser(@RequestBody User user) {
        passwordHelper.encryptPassword(user);
        userService.createUser(user);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("/login")
    public ServerResponse<?> login(@RequestBody User user, HttpSession session) {
        String username = user.getUsername();
        String password = user.getPassword();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        } catch (LockedAccountException e) {
            return ServerResponse.createByErrorMessage("账户已停用");
        } catch (ExcessiveAttemptsException e) {
            return ServerResponse.createByErrorMessage("用户名或密码错误次数过多");
        }

        if (subject.isAuthenticated()) {
            session.setAttribute("username", username);
            return ServerResponse.createBySuccess();
        }

        return ServerResponse.createBySuccess();
    }

    @PostMapping("/test")
    //@RequiresPermissions("user:add")
    @RequiresRoles("超级管理员2")
    //@RequiresGuest
    public ServerResponse test() {
        System.out.println(1111);
        return ServerResponse.createBySuccess();
    }
}
