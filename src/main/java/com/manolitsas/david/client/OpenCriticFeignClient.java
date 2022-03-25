package com.manolitsas.david.client;

import com.manolitsas.david.client.model.GameResponse;
import com.manolitsas.david.config.OpenCriticFeignClientConfiguration;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "${open-critic-client.name}",
    url = "${open-critic-client.url}",
    configuration = OpenCriticFeignClientConfiguration.class)
public interface OpenCriticFeignClient {

  @GetMapping(value = "/game/{id}")
  GameResponse findGameById(@PathVariable("id") String id);

  @GetMapping(value = "/game/recently-released")
  List<GameResponse> findRecentReleases();

  @GetMapping(value = "/game/upcoming")
  List<GameResponse> findUpcomingReleases();
}
