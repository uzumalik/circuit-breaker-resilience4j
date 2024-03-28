package com.uzu.circuitbreakerresilience4j.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uzu.circuitbreakerresilience4j.CircuitBreakerConfig;
import com.uzu.circuitbreakerresilience4j.model.response.Account;
import com.uzu.circuitbreakerresilience4j.model.response.User;
import com.uzu.circuitbreakerresilience4j.service.vendor.SampleServiceClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Uzair
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private List<User> userList;

    @Autowired
    private SampleServiceClient sampleServiceClient;

    @PostConstruct
    private void initialize(){
        userList = Arrays.asList(
                User.builder()
                        .id(100L)
                        .name("Bill Gates")
                        .age(67)
                        .build(),
                User.builder()
                        .id(101L)
                        .name("Elun Musk")
                        .age(57)
                        .build(),
                User.builder()
                        .id(102L)
                        .name("Bill Murray")
                        .age(60)
                        .build()
        );
    }

    @Override
    public User getUser(Long id) {
        log.info("trying to find user with userId {}",id);
        User user = userList
                        .stream()
                        .filter( usr -> usr.getId().equals(id))
                        .findFirst()
                        .orElseThrow( () -> new RuntimeException("User not found"));

        List<Account> accountList = sampleServiceClient.getAccountList(id);

        user.setAccounts(accountList);
    return user;
    }


}
