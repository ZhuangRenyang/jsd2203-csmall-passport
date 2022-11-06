package cn.tedu.jsd2203.csmall.passport;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@SpringBootTest
public class JwtTest {
    @Test
    public void testGeneratedJet() {

        //存入JWT的数据
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", 123);
        claims.put("name", "tom");

        //System.currentTimeMillis();//1970年1月1日0时0分0秒 到 此时此刻 的毫秒值
        //过期时间： 1分钟
        Date expirationDate = new Date(System.currentTimeMillis() + 1 * 60 * 1000);

        //JWT的组成部分：header（头） ，payload（载荷），signature（签名）
        String jwt = Jwts.builder()
                //header: 用于配置算法与此结果数据的类型
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                //payload: 用于配置需要封装到JWT的数据
                .setClaims(claims)
                .setExpiration(expirationDate)
                //signature:用于指定算法与密匙
                .signWith(SignatureAlgorithm.HS256, "udiasjia")
                //打包
                .compact();
        log.debug("{}", jwt);
    }

    @Test
    public void testParseJwt(){
        String jwt = "eyJ0eXAiOiJqd3QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEyMywiZXhwIjoxNjY3NzIwNzg1fQ.I9gTWETGk68qZArKUGdnC9lr7n-CdXkUbrgotWMqj_Q";
       Claims claims = Jwts.parser().setSigningKey("udiasjia").parseClaimsJws(jwt).getBody();
        Object id = claims.get("id");
        Object name = claims.get("name");
        log.debug("id:{},name:{}",id,name);
    }

}
