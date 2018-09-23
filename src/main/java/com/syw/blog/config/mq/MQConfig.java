package com.syw.blog.config.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    //声明队列
    @Bean
    public Queue requestQueue() {
        return new Queue("hello", true); //true表示持久化该队列
    }

    @Bean
    public Queue requestQueue2() {
        return new Queue("hi", true);
    }

    //声明交互器
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(requestQueue()).to(topicExchange()).with("key.1");
    }

    @Bean
    public  Binding binding2() {
        return BindingBuilder.bind(requestQueue2()).to(topicExchange()).with("key.#");
    }

}
