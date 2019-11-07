package com.cyl.springboottest1.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbittemplate;

    public void sendmsg(String msg){
        rabbittemplate.convertAndSend("top1","key.add",msg);
        System.out.println("bootsender");
    }
}
