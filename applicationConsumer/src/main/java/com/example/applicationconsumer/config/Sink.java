package com.example.applicationconsumer.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Sink {

    //接收队列1
    String INPUT_1 = "input1";

    @Input(Sink.INPUT_1)
    SubscribableChannel input1();
}
