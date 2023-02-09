package com.example.applicationconsumer.service.impl;

import com.example.applicationconsumer.service.FeignDemoInterface;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignFallBack implements FallbackFactory<FeignDemoInterface> {
    @Override
    public FeignDemoInterface create(Throwable throwable) {
        return new FeignDemoInterface() {
            @Override
            public String getFeignDemo(Integer id) {
                System.out.println(throwable);
                if(throwable instanceof FeignException) {
                    System.out.println("InternalServerError");
                    return "远程服务报错";
                }else if(throwable instanceof RuntimeException) {
                    return "请求时异常：" + throwable;
                }else {
                    return "其他异常";
                }
            }

        };
    }
}
