package com.ingesoft.carro.controller;

import com.ingesoft.carro.model.TestModel;
import com.ingesoft.carro.service.interfaces.TestService;
import com.ingesoft.carro.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private TestService testService;
    private RedisService redisService;

    @Autowired
    public TestController(TestService testService, RedisService redisService) {
        this.testService = testService;
        this.redisService = redisService;
    }

    @GetMapping(path = "{id}")
    @Cacheable(value = "Employee", key = "#id")
    public ResponseEntity<TestModel> getTest(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(testService.getTest(id));
    }

    @GetMapping
    public ResponseEntity<TestModel> getAll() {
        return ResponseEntity.ok(testService.getAll());
    }

    @PostMapping
    public ResponseEntity<TestModel> saveTest(@RequestBody final TestModel test) {
        return ResponseEntity.ok(testService.saveTest(test));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTest(@PathVariable("id") Long id) {
        if (redisService.hasKey("TEST", id.toString())) {
            redisService.remove("TEST", id.toString());
            return ResponseEntity.ok("Test eliminado correctamente.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


