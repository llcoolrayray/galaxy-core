package com.micro.service.galaxycore.bean;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/22 16:25
 * @description
 */
@Configuration
public class ShiroConfig {
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        //factoryBean.setUnauthorizedUrl("/login");
        //factoryBean.setLoginUrl("/login");

        // 设置shiro的拦截规则
        // anon 匿名用户可访问
        // authc  认证用户可访问
        // user 使用 Remember Me 的用户可访问
        // perms 拥有对应权限可访问
        // role  拥有对应的角色可访问
        Map<String, String> filterMap = new HashMap<>(8);
        //filterMap.put("/", "anon");
        //filterMap.put("/login", "anon");
       // filterMap.put("/signup", "anon");
        //filterMap.put("/static/**", "anon");
        //filterMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);

        return factoryBean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(customRealm);
        return manager;
    }

    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 加密方式
        hashedCredentialsMatcher.setHashAlgorithmName("SHA-256");
        // 加密次数
        hashedCredentialsMatcher.setHashIterations(2);
        // 密码为 16 进制
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return hashedCredentialsMatcher;
    }

    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher matcher) {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(matcher);
        return customRealm;
    }
}
