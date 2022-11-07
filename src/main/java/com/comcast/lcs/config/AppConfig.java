package com.comcast.lcs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.guava.GuavaModule;

@Configuration
public class AppConfig {

  @Bean
  public GuavaModule guavaModule(){
    return new GuavaModule();
  }
}
