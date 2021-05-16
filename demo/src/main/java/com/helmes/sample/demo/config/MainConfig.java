package com.helmes.sample.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MainConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Override
    public void configure (WebSecurity webSecurity){
        webSecurity.ignoring().anyRequest();
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().permitAll();
        httpSecurity.csrf().disable();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
