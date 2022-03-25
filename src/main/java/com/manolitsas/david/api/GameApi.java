package com.manolitsas.david.api;

import com.manolitsas.david.model.Game;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
public interface GameApi {

  @GetMapping(value = "/game/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Game> getGameById(@PathVariable String id);

  @GetMapping(value = "/game/recent-releases", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Game>> getRecentReleases();

  @GetMapping(value = "/game/coming-soon", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<Game>> getUpcomingReleases();
}
