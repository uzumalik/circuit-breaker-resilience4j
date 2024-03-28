package com.uzu.circuitbreakerresilience4j.model.response;

import com.uzu.circuitbreakerresilience4j.constants.AccountType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mohammad Uzair
 **/
@Getter
@Setter
@Builder
public class Account {

    private Long userId;

    private Long accountNumber;

    private AccountType accountType;

    private Double balance;


}

