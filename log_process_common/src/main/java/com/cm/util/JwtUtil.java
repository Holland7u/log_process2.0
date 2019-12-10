package com.cm.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;

/**
 * Created by WF on 2019-11-07 15:50
 */
@ConfigurationProperties("jwt.config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtUtil {
    private long ttl;
    private String key;
    //1.创建jwt的token串
    public String createJwt(String id,String subject,String roles){
        //1.1)得到当前时间
        long timeMillis = System.currentTimeMillis();
        //1.2)设置过期时间
        Date expireTime = null;
        //1.3)得到jwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setSubject(subject)
                .claim("roles", roles)
                .setIssuedAt(new Date(timeMillis))
                .signWith(SignatureAlgorithm.HS256,key);
        //1.4)设置过期时间
        if(ttl > 0){
            expireTime = new Date(timeMillis + ttl);
            jwtBuilder.setExpiration(expireTime);
        }
        //1.5)返回得到的token信息
        return jwtBuilder.compact();
    }
    //2.解析token信息
    public Claims parseJwt(String token){
        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}
