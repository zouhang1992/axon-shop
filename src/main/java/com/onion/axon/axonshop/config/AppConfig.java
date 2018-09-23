package com.onion.axon.axonshop.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(
        {OrderConfig.class, ProductConfig.class}
)
public class AppConfig {
}
