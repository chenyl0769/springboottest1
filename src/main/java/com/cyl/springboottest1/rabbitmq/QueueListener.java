package com.cyl.springboottest1.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class QueueListener {
    @RabbitHandler
    @RabbitListener(queues = "bootqueue.que1")
    public void rec(String msg){
        System.out.println("bootqueue.que1："+msg);
    }
    @RabbitHandler
    @RabbitListener(queues = "bootqueue.que2")
    public void rec2(String msg){
        System.out.println("bootqueue.que2："+msg);
    }
}
