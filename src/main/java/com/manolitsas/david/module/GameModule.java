package com.manolitsas.david.module;

import com.manolitsas.david.client.OpenCriticFeignClient;
import com.manolitsas.david.client.model.GameResponse;
import com.manolitsas.david.exception.CustomApiException;
import com.manolitsas.david.mapper.GameMapper;
import com.manolitsas.david.model.Game;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GameModule {

  private final OpenCriticFeignClient openCriticClient;
  private final GameMapper mapper;

  public Game getGame(String id) {
    GameResponse gameResponse = openCriticClient.findGameById(id);

    if (gameResponse == null) {
      throw CustomApiException.notFoundException(String.format("No game with id %s found", id));
    }

    return mapper.toGame(gameResponse);
  }


  public List<Game> getRecentReleases() {
    List<GameResponse> response = openCriticClient.findRecentReleases();

    if (response == null) {
      throw new CustomApiException(
          "No recent games found", "500", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    return response.stream().map(mapper::toGame).collect(Collectors.toList());
  }


  public List<Game> getUpcomingReleases() {
    List<GameResponse> response = openCriticClient.findUpcomingReleases();

    if (response == null) {
      throw new CustomApiException(
              "No upcoming games found", "500", HttpStatus.INTERNAL_SERVER_ERROR, "");
    }

    return response.stream().map(mapper::toGame).collect(Collectors.toList());
  }
}
