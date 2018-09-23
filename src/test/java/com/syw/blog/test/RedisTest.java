package com.syw.blog.test;

import com.syw.blog.config.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void RedisTest() {

        //写入缓存
        redisService.set("result", "oob");
        String str = (String) redisService.get("result");
        System.out.println("redis中str的值：" + str);

        //写入缓存，设置时效时间
        redisService.set("orders", 100, 10l);
        Integer orders = (Integer) redisService.get("orders");
        System.out.println("redis中有时效时间的orders的值：" + orders);

        try {
            Thread.sleep(9000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //再次读取有时效的redis缓存的orders的值
        orders = (Integer) redisService.get("orders");
        System.out.println("再次读取有时效的redis缓存的orders的值：" + orders);

        //删除对应的value

        //删除前操作
        redisService.set("app", "app");
        String app = (String) redisService.get("app");
        System.out.println("redis中app的值：" + app);

        //进行删除操作，再次读取redis缓存中app的值
        redisService.remove("app");
        app = (String) redisService.get("app");
        System.out.println("再次读取redis缓存中的app的值：" + app);

        //判断缓存中是否存在对应的value
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        redisService.set("list", list);
        List<Integer> l = (List<Integer>) redisService.get("list");
        System.out.println("redis中list的值：" + l);
        System.out.println("redis中list是否有对应的value存在：" + redisService.exists("list"));

        //删除操作后再查看是否有对应的value存在
        redisService.remove("list");
        System.out.println("再次查看redis中是否有对应的value存在：" + redisService.exists("list"));

        //添加列表
        redisService.lPush("lll", 123);
        redisService.lPush("lll", 456);
        redisService.lPush("lll", 789);
        List<Object> lll = (List<Object>) redisService.lRange("lll", 0, 10);
        System.out.println("redis中lll的值：" + lll);

        //添加集合
        redisService.add("add", "string");
        redisService.add("add", "integer");
        redisService.add("add", "short");
        redisService.add("add", "integer");
        Set<Object> add = (Set<Object>) redisService.setMembers("add");
        System.out.println("redis中add的值：" + add);

        //添加有序集合
        redisService.zAdd("plp","string", 1);
        redisService.zAdd("plp", "integer", 2);
        redisService.zAdd("plp", "short", 3);
        redisService.zAdd("plp", "integer", 2);
        Set<Object> plp = (Set<Object>) redisService.rangeByScore("plp", 1,4);
        System.out.println("redis中plp的值：" + plp);

    }

}
