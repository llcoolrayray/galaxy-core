package com.micro.service.galaxycore.util;

import com.micro.service.galaxycore.pojo.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author Wang.Rui.Barney
 * @date 2024/01/23 11:02
 * @description
 */
@Component
public class PasswordHelper {
    private final int hashIterations = 2;
    private final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private final String algorithmName = "SHA-256";

    public void encryptPassword(User user) {
        // 生成随机盐
        String salt = randomNumberGenerator.nextBytes().toHex();
        user.setSalt(salt);

        // 结合用户密码和盐值进行哈希
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }
}
