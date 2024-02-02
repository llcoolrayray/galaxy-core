package com.micro.service.galaxycore.controller;

import com.micro.service.galaxycore.common.ServerResponse;
import com.micro.service.galaxycore.pojo.User;
import com.micro.service.galaxycore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ray
 * @date 2019/8/17 13:13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

//    @PostMapping("/signup")
//    public ServerResponse<?> getUser(@RequestBody User user) {
//        userService.createUser(user);
//        return ServerResponse.createBySuccess();
//    }
//
//    @PostMapping("/login")
//    public ServerResponse<?> login(@RequestBody User user) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//
//        return ServerResponse.createBySuccess();
//    }

    @GetMapping("/test")
    public ServerResponse test() {
        System.out.println(1111);
        return ServerResponse.createBySuccess(userService.findUserByUsername("barney"));
    }
}
