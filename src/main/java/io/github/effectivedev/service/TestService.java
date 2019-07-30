package io.github.effectivedev.service;

import io.github.effectivedev.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    @Autowired
    TestRepository repository;

    public void testService(){
        repository.testRepository();
    }
}
