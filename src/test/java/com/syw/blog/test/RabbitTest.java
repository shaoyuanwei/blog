package com.syw.blog.test;

import com.syw.blog.mq.producer.MQProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RabbitTest {

    @Autowired
    private MQProducer mqProducer;

    @Test
    public void sendTest() throws Exception {
        while (true) {
            String msg = new Date().toString();
            mqProducer.send(msg);
            Thread.sleep(1000);
        }
    }

}
