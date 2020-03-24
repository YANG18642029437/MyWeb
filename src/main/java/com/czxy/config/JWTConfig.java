package com.czxy.config;

import com.czxy.user.domain.User;
import com.czxy.utils.RasUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.PublicKey;

@ConfigurationProperties("com.czxy.jwtconfig")
@Data
@Configuration
public class JWTConfig {
    private String pubKeyPath;
    private String priKeyPath;

    @Bean
    public ThreadLocal<User> threadLocal(){
        ThreadLocal<User> threadLocal = new ThreadLocal<>();
        return threadLocal;
    }

    @Bean
    public PublicKey publicKey(){
        PublicKey publicKey = null;
        try {
            publicKey = RasUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            try {
                RasUtils.generateKey(pubKeyPath,priKeyPath,"1234567890");
                publicKey = RasUtils.getPublicKey(pubKeyPath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return publicKey;
    }

    @Bean
    public PrivateKey privateKey(){
        PrivateKey privateKey = null;
        try {
            privateKey = RasUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            try {
                RasUtils.generateKey(pubKeyPath,priKeyPath,"1234567890");
                privateKey = RasUtils.getPrivateKey(priKeyPath);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
//        System.out.println("我执行了");
        return privateKey;
    }

}
