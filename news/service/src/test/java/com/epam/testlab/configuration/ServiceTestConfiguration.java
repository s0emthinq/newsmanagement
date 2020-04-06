package com.epam.testlab.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.epam.lab.service.impl","com.epam.lab.configuration"})
public class ServiceTestConfiguration {
}
