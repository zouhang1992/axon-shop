package com.onion.axon.axonshop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
        {AxonConfig.class}
)
public class AppConfig {
}
