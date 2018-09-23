package com.syw.blog.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MQConsumer {

    @RabbitListener(queues = "hello")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + "接收到来自hello的消息" + msg);
        return msg.toUpperCase();
    }

    @RabbitListener(queues = "hi")
    public String processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + "接收到来自hi的消息" + msg);
        return msg.toUpperCase();
    }

}
