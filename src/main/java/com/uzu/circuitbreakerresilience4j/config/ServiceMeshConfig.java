package com.uzu.circuitbreakerresilience4j.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Mohammad Uzair
 **/
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "service-mesh")
@Getter
@Setter
public class ServiceMeshConfig {


    @NotNull
    private HostConfig sampleApiService;
    @Getter
    @Setter
    public static class HostConfig {

        private String baseUri;
    }
}
