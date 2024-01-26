package com.micro.service.galaxycore.bean;

import com.micro.service.galaxycore.dao.UserMapper;
import com.micro.service.galaxycore.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 16:30
 * @description
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取当前登录的用户信息
        Subject subject = SecurityUtils.getSubject();
        Account account = (Account) subject.getPrincipal();

        //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(String.valueOf(account.getRoles()));

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermissions(account.getStringPermissions());

        return info;
    }

    /**
     * 认证
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 参数authenticationToken就是传递的 subject.login(token)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从token中获取用户名
        String username = token.getUsername();
        // 根据用户名从数据库查询用户安全数据
        User user = userMapper.findUserByUsername(username);

        if (user == null) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }

        // 获取用户的盐值，用于密码的哈希比较
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getCredentialsSalt());

        return new SimpleAuthenticationInfo(username, user.getPassword(), credentialsSalt, getName());
    }
}
