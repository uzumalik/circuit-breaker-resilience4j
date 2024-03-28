package com.uzu.circuitbreakerresilience4j.service.vendor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uzu.circuitbreakerresilience4j.CircuitBreakerConfig;
import com.uzu.circuitbreakerresilience4j.model.response.Account;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Mohammad Uzair

 **/
@Service
@Slf4j
public class SampleServiceClient {

    @Autowired
    private CircuitBreakerConfig config;

    @Autowired
    private ObjectMapper mapper;


    @CircuitBreaker(name = "accounts", fallbackMethod = "getAccountsFallback")
    public List<Account> getAccountList(Long id) {
        log.info("calling sample service to get user's accounts");

        ResponseEntity<List<com.sample.model.Account>> response = config
                .getAccountController()
                .getAccountsWithHttpInfo(id)
                .block();
        /*
        List<com.sample.model.Account> accountListWithResponseSpec = config
                .getAccountController().getAccountsWithResponseSpec(id)
        .bodyToFlux(com.sample.model.Account.class)
                .collectList().block();

        List<com.sample.model.Account> accountListWithFlux = config
                .getAccountController()
                        .getAccounts(id)
                                .collectList().block();*/
        log.info("received response");
        assert response != null;
        List<Account> accountList = Objects.requireNonNull(response
                        .getBody())
                .stream()
                .map(acc -> mapper.convertValue(acc, Account.class))
                .toList();
        log.info("parse response, size {}", accountList.size());

        return accountList;

    }

    public List<Account> getAccountsFallback(Long id, Throwable throwable) {
        log.info("fallback triggered");
        return Collections.emptyList();
    }
}