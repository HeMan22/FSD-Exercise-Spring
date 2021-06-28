package com.practice.mycontactapi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="com.practice.mycontactapi")
@EnableWebMvc
public class WebApplicationConfig {

}
