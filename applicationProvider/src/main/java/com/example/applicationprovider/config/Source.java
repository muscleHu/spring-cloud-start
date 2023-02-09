package com.example.applicationprovider.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Source {

    //发送队列1
    String OUTPUT_1 = "output1";

    @Output(Source.OUTPUT_1)
    SubscribableChannel output1();
}
