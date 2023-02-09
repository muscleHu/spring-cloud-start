package com.example.applicationconsumer.service.impl;

import com.example.applicationconsumer.service.FeignDemoInterface;
import org.springframework.stereotype.Component;

@Component
public class FallBack implements FeignDemoInterface {
    @Override
    public String getFeignDemo(Integer id) {
        return "is fall back";
    }
}
