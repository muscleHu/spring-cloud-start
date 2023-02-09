package com.example.applicationconsumer.service;

import com.example.applicationconsumer.service.impl.FallBack;
import com.example.applicationconsumer.service.impl.FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*fallback = FallBack.class*/
@FeignClient(name = "eureka-provider",fallbackFactory = FeignFallBack.class)
public interface FeignDemoInterface {

    @GetMapping(value = "/demo/helloCloud")
    String getFeignDemo(@RequestParam("id")Integer id);
}
