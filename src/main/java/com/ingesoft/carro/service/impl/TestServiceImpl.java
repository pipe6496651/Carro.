package com.ingesoft.carro.service.impl;

import com.ingesoft.carro.model.TestModel;
import com.ingesoft.carro.service.interfaces.TestService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RedisService redisService;


    @Override
    public TestModel getAll() {
        if (redisService.hasKey("TEST", "ALL")) {
            return redisService.get("TEST", "ALL");
        } else
            return null;
    }

    @Override
    public TestModel getTest(Long id) {
        if (redisService.hasKey("TEST", id.toString())) {
            return redisService.get("TEST", id.toString());
        } else
            return null;
    }

    @Override
    public TestModel saveTest(TestModel test) {
        redisService.set("TEST", test.getId().toString(), test, 1, java.util.concurrent.TimeUnit.HOURS);
        return test;
    }

    @Override
    public void deleteTest(Long id) {
        if (redisService.hasKey("TEST", id.toString())) {
            redisService.remove("TEST", id.toString());
        }
    }
}    
