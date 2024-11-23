package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class SpringbootprojectApplicationTests {

    //@Test
    void contextLoads() {
    }

    /**
     * 生成jwt令牌
     */
    //@Test
    void testJwt(){
        Map<String, Object> claims =new HashMap<>();
        claims.put("username","zhangsan");
        claims.put("password","123456");
        //SignatureAlgorithm.HS256编码方式需要长度为256,itheima用的UTF-8编码长度56,不够256会抛异常
        String jwt= Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,io.jsonwebtoken.security.Keys.hmacShaKeyFor(("itheima" +
                        "abcdefghijklmnopqrstuvwxyz").getBytes(StandardCharsets.UTF_8)))
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
        System.out.println(jwt);
    }

    /**
     * 解析jwt令牌
     */
    //@Test
    public void testPassJwt(){
        Claims claims = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(("itheima" +
                        "abcdefghijklmnopqrstuvwxyz")
                        .getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJuYW1lIjoiemhhbmdzYW4iLCJleHAiOjE3MzE5MDM1NTB9.MdnYFaJzCI4rauZNWezwY9miwfIvyL35Z5-ppcOa4a8")
                .getBody();
        System.out.println(claims);
    }
}
