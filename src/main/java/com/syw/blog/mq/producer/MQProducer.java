package com.syw.blog.mq.producer;

import com.rabbitmq.client.Return;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class MQProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
        System.out.println("消息生产者初始化成功");
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getCorrelationId() + "发送失败");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if (ack) {
            System.out.println("消息发送成功" + correlationData);
        } else {
            System.out.println("消息发送失败" + cause);
        }

    }

    //发送消息，不需要实现任何接口，供外部使用
    public void send(String msg) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("开始发送消息：" + msg.toLowerCase());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", msg, correlationData).toString();
        //实现消息发送方法
//        rabbitTemplate.send();//发消息，参数类型为org.springframework.amqp.core.Message。
//        rabbitTemplate.convertAndSend();//转换并发送消息。将参数对象转换为org.springework.amqp.core.Message后发送。
//        rabbitTemplate.convertSendAndReceive();//转换并发送消息，且等待消息者返回响应信息。
        System.out.println("结束发送消息" + msg.toLowerCase());
        System.out.println("消费者响应" + response + "消费处理完成");
    }

}
