package com.example.applicationconsumer.controller;

import com.example.applicationconsumer.service.FeignDemoInterface;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback ="fallBackAction" )
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignDemoInterface feignDemoInterface;

    @RequestMapping("/getProvider")
    public String getProvider(){
        //return restTemplate.getForEntity("http://eureka-provider/demo/helloCloud?param="+"name",String.class).getBody();
        return restTemplate.getForEntity("http://localhost:8002/demo/helloCloud?param="+"name",String.class).getBody();

    }

    //超时降级演示
    /*@HystrixCommand(fallbackMethod = "fallBackAction",commandProperties = {
            //超过1.5秒就降级自己
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })*/

    /*@HystrixCommand(fallbackMethod = "fallBackAction",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500"),
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "70000"),    //时间窗口期（范围）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")    //失败率达到多少后跳闸
    }*//*,ignoreExceptions = Exception.class*//*)*/
    //@HystrixCommand
    @RequestMapping("/demoFeign")
    public String getDemoFeign(Integer id) throws Exception {
        if(id>1){
            throw new RuntimeException();
        }
        return feignDemoInterface.getFeignDemo(id);
    }

    //降级方法
    public String fallBackAction(Integer id){
        return "consumer服务降级，调用失败";
    }

}
