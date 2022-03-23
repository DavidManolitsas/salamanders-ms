package com.manolitsas.david.config;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenCriticFeignClientConfiguration {

  @Bean
  public Decoder feignDecoder() {
    return new JacksonDecoder();
  }
}
