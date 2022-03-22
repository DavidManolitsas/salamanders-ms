package com.manolitsas.david.api;

import com.manolitsas.david.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Validated
public interface GameApi {

    @GetMapping(value = "/game/{id}")
    ResponseEntity<Game> getGameById(@PathVariable String id);
}
