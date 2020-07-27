package com.example.email;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
