package com.cyl.springboottest1.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置类
 */
@Configuration
public class QueueConfig {
    //配置队列
    @Bean
    public Queue getqueue(){
        return new Queue("bootqueue.que1");

    }
    //配置队列
    @Bean
    public Queue getqueue2(){
        return new Queue("bootqueue.que2");

    }
    //主题模式
    @Bean
    public TopicExchange topicExchange1(){
        return new TopicExchange("top1");
    }
    //主题模式
    @Bean
    public TopicExchange topicExchange2(){
        return new TopicExchange("top2");
    }
    //队列与交换机绑定
    @Bean
    public Binding bingtop1(Queue getqueue,TopicExchange topicExchange1){
        return BindingBuilder.bind(getqueue).to(topicExchange1).with("key.#");
    }
    //队列与交换机绑定
    @Bean
    public Binding bingtop2(Queue getqueue2,TopicExchange topicExchange1){
        return BindingBuilder.bind(getqueue2).to(topicExchange1).with("key.add");
    }
}
