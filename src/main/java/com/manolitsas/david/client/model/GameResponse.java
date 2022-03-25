package com.manolitsas.david.client.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameResponse {

  @JsonProperty("name")
  private String name;

  @JsonProperty("averageScore")
  private Double averageScore;

  @JsonProperty("description")
  private String description;

  @JsonProperty("Companies")
  private List<Company> companies;

  @JsonProperty("firstReleaseDate")
  private String firstReleaseDate;

  @JsonProperty("mastheadScreenshot")
  private Screenshot mastheadScreenshot;

  @JsonProperty("reviewSummary")
  private ReviewSummary reviewSummary;

  @JsonProperty("verticalLogoScreenshot")
  private Screenshot verticalLogoScreenshot;

  @JsonProperty("url")
  private String url;
}
