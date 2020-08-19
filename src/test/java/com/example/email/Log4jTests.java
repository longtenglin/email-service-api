package com.example.email;

import com.example.email.entity.User;
import com.example.email.utils.ErrorMessage;
import com.example.email.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Log4jTests {

    @Test
    public void log4jTest(){
        log.error("这是error级别");
        log.debug("这是debug级别");
        log.warn("This is warn");
        log.info("This is info");
        log.trace("This is trace");
        System.out.println("1234");
    }

    @Test
    public void ErrorMessage(){
        System.out.println(ErrorMessage.VerifyCode.getValue());
    }

    @Test
    public void testToken(){
        Map<String,String> map = new HashMap<>();
        map.put("username", "419405209@qq.com");
        map.put("password","123456a");
        map.put("code","784564");
        System.out.println(map.toString());
        String token = TokenUtils.buildJWT(map.toString());
        System.out.println(token);
        String loginJson = TokenUtils.verifyToken(token);
        System.out.println(loginJson);
    }
}
