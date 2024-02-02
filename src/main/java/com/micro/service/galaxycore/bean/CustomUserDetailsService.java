package com.micro.service.galaxycore.bean;

import com.micro.service.galaxycore.dao.UserMapper;
import com.micro.service.galaxycore.pojo.Role;
import com.micro.service.galaxycore.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wang.Rui.Barney
 * @date 2024/02/01 15:25
 * @description
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<String> roles = userMapper.findUserByUsername(username).getRoles().stream().map(Role::getName).collect(Collectors.toList());
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), simpleGrantedAuthorityList);
    }
}
