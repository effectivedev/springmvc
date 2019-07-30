package io.github.effectivedev.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.transaction.TestContextTransactionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        log.info("Test3");
        return "Hello World";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
