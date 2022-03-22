package com.manolitsas.david.module;

import com.manolitsas.david.client.OpenCriticFeignClient;
import com.manolitsas.david.client.model.OpenCriticGameResponse;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.mapper.GameMapper;
import com.manolitsas.david.model.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameModule {

  private final OpenCriticFeignClient openCriticClient;
  private final GameMapper mapper;

  public Game getGame(String id) {
    OpenCriticGameResponse openCriticResponse = openCriticClient.findGameById(id);

    if (openCriticResponse.getName().isEmpty()) {
      throw CustomApiException.notFoundException(String.format("No game with id %s found", id));
    }

    return mapper.toGame(openCriticResponse);
  }
}
