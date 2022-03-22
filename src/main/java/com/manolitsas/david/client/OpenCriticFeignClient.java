package com.manolitsas.david.client;

import com.manolitsas.david.client.model.OpenCriticGameResponse;
import com.manolitsas.david.config.OpenCriticFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "${open-critic-client.name}",
    url = "${open-critic-client.url}",
    configuration = OpenCriticFeignClientConfiguration.class)
public interface OpenCriticFeignClient {

  @GetMapping(value = "/game/{id}")
  OpenCriticGameResponse findGameById(@PathVariable("id") String id);
}
