package com.manolitsas.david.api;

import com.manolitsas.david.model.Game;
import com.manolitsas.david.module.GameModule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class GameController implements GameApi {

  private final GameModule gameModule;

  @Override
  public ResponseEntity<Game> getGameById(String id) {
    return ResponseEntity.ok(gameModule.getGame(id));
  }

  @Override
  public ResponseEntity<List<Game>> getRecentReleases() {
    return ResponseEntity.ok(gameModule.getRecentReleases());
  }

  @Override
  public ResponseEntity<List<Game>> getUpcomingReleases() {
    System.out.println("getting upcoming");
    return ResponseEntity.ok(gameModule.getUpcomingReleases());
  }
}
