package io.github.effectivedev.controller;


import io.github.effectivedev.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.transaction.TestContextTransactionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        testService.testService();
        return "Hello World";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
