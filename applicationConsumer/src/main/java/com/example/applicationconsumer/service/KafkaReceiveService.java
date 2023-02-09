package com.example.applicationconsumer.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import com.example.applicationconsumer.config.Sink;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

//消息接收端，stream给我们提供了Sink,Sink源码里面是绑定input的，要跟我们配置文件的input关联的。
@EnableBinding(Sink.class)
@Service
public class KafkaReceiveService {

    @StreamListener(target = Sink.INPUT_1)
    public void process(Message<?> message) {
        System.out.println("接收到消息----------->>>"+message.getPayload());
        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
        if (acknowledgment != null) {
            System.out.println("Acknowledgment provided");
            acknowledgment.acknowledge();
        }
    }
}
