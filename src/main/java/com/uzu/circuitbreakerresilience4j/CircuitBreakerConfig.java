package com.uzu.circuitbreakerresilience4j;

import com.sample.ApiClient;
import com.sample.api.AccountControllerApi;
import com.uzu.circuitbreakerresilience4j.config.ServiceMeshConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mohammad Uzair
 **/

@Configuration
public class CircuitBreakerConfig {

    @Autowired
    private ServiceMeshConfig meshConfig;

    public com.sample.api.AccountControllerApi getAccountController(){
        com.sample.api.AccountControllerApi controllerApi = new AccountControllerApi(new ApiClient());
        controllerApi.getApiClient().setBasePath(meshConfig.getSampleApiService().getBaseUri());
        return controllerApi;
    }
}
