package com.manolitsas.david.model;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Game {
  private String releaseYear;
  private String name;
  private String description;
  private String developer;
  private String publisher;
  private Integer score;
  private URL imageUrl;
}
