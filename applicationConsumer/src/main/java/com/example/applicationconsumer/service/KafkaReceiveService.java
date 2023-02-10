package com.example.applicationconsumer.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.example.applicationconsumer.config.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Map;

//消息接收端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的input关联的。
@EnableBinding(Sink.class)
@Service
public class KafkaReceiveService {

    //message.getPayload() 为啥收到的是byte数组
    @StreamListener(Sink.INPUT_1)
    public void process(Message<?> message) {
        System.out.println("Message<?>接收到消息----------->>>"+new String((byte[])message.getPayload()));
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }

    @StreamListener(Sink.INPUT_1)
    public void process1(@Payload String msg,@Headers Map map) {
        System.out.println("@Payload接收到消息----------->>>"+msg+map);
    }
}
