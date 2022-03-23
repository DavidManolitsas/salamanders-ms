package com.manolitsas.david.model;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Game {
  private String name;
  private String description;
  private String summary;
  private String developer;
  private String publisher;
  private String releaseYear;
  private Integer score;
  private URL imageUrl;
}
