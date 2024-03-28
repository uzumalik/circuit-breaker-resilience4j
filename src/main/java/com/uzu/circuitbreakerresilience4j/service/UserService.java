package com.uzu.circuitbreakerresilience4j.service;


import com.uzu.circuitbreakerresilience4j.model.response.User;

public interface UserService {

    User getUser(Long id);
}
