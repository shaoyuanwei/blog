package com.syw.blog.test;

import com.syw.blog.config.service.RedisService;
import com.syw.blog.entity.Account;
import com.syw.blog.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpSession;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(value = SpringJUnit4ClassRunner.class)
public class Redis2Test {

    private static Logger logger = LoggerFactory.getLogger(Redis2Test.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private AccountService accountService;

    @Test
    public void redisTest() {
        Account account = accountService.queryAccountById(1);
        logger.info("用户{}的信息为{}", account.getAccount(), account.toString());
        redisService.set("account" + account.getId(), account);
        Account a = (Account) redisService.get("account" + account.getId());
        logger.info("用户{}的信息为{}", a.getAccount(), a.toString());
    }

}
