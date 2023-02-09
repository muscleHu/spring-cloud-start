package com.example.applicationprovider.service;

import com.example.applicationprovider.config.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

//这个注解给我们绑定消息通道的，Source是Stream给我们提供的，可以点进去看源码，可以看到output和input,这和配置文件中的output，input对应的。
@EnableBinding(Source.class)
@Service
public class KafkaSendService {

    @Autowired
    private Source source;

    public void sendMsg(String msg) {
        source.output1().send(MessageBuilder.withPayload(msg).build());
    }
}
