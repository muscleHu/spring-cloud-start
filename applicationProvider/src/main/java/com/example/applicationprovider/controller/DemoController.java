package com.example.applicationprovider.controller;

import com.example.applicationprovider.service.KafkaSendService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RequestMapping("/demo")
@RestController
//@DefaultProperties(defaultFallback = "fallBackAction")
public class DemoController {

    @Autowired
    private KafkaSendService kafkaSendService;

    /*@HystrixCommand(fallbackMethod = "fallBackAction",commandProperties = {
            //2秒钟以内就是正常的业务逻辑
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000"),
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "70000"),    //时间窗口期（范围）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })*/
    /*@HystrixCommand*/
    @RequestMapping("/helloCloud")
    public String helloCloud(Integer id) throws Exception {
        //TimeUnit.SECONDS.sleep(3);
        if(id<0){
            throw new Exception();
        }
        return "hello cloud "+id;
    }

    //降级后方法，上面方法出问题,我来处理，返回一个出错信息
    public String fallBackAction(Integer id) {
        return "provider,服务降级,访问请求失败了，服务不可用";
    }


    @RequestMapping("/sendMsg")
    public void sendKafkaMsg(){
        kafkaSendService.sendMsg("{'1':访问1}");
    }
}
