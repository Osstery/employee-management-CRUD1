package com.learn;

import com.learn.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootTliasApplicationTests {

    // 测试生成jwt
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "learn")// 设置签名算法
                .setClaims(claims)// 设置自定义数据（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 36000 * 1000))// 设置过期时间为一个小时
                .compact();//返回jwt字符串
        System.out.println(jwt);
    }

    @Autowired
    private ApplicationContext applicationContext;//IOC容器对象

    @Test
    public void testGetBean(){
        //根据bean的名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);

        //根据bean的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        //根据bean的id和类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);
    }

//    @Test
//    public void testParseJwt() {
//        String jwt = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTczMDgwMjEyNX0.ub4FDmEHLWmTGZA83OZm3Soh8uJpgrhv-NYkwiS_wWE";
//        Claims claims = Jwts.parser()
//                .setSigningKey("learn")
//                .parseClaimsJws(jwt)
//                .getBody();
//        System.out.println(claims);
//    }

}
