package web.tliaswebmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {


    // 测试生成JWT令牌

    @Test
    public void testGennerateJwt() {
        Map<String, Object> datemap = new HashMap<>();
        datemap.put("id", 1);
        datemap.put("username", "张三");

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, "dGxpYXM=")// 设置签名算法
                .addClaims(datemap)// 添加自定义声明
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))// 设置过期时间，1小时
                .compact();// 生成JWT令牌
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiLlvKDkuIkiLCJleHAiOjE3NzcxNzQ4OTV9.g7_c4VN8jlaj1AwO0uf3YnsqP_SHbOEijpL8NPRmzb8";
        Claims claims = Jwts.parser().setSigningKey("dGxpYXM=")
                .parseClaimsJws(token)
                .getBody();

    }
}
