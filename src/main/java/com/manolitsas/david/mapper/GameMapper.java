package com.manolitsas.david.mapper;

import com.manolitsas.david.client.model.*;
import com.manolitsas.david.model.Game;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.*;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

  @Mapping(target = "name", source = "response.name")
  @Mapping(target = "score", source = "response.averageScore", qualifiedByName = "DoubleToInt")
  @Mapping(target = "description", source = "response.description")
  @Mapping(
      target = "summary",
      source = "response.reviewSummary",
      qualifiedByName = "ExtractReviewSummary")
  @Mapping(
      target = "developer",
      source = "response.companies",
      qualifiedByName = "ExtractDeveloper")
  @Mapping(
      target = "publisher",
      source = "response.companies",
      qualifiedByName = "ExtractPublisher")
  @Mapping(
      target = "releaseYear",
      source = "firstReleaseDate",
      qualifiedByName = "ExtractReleaseYear")
  @Mapping(
      target = "releaseDate",
      source = "firstReleaseDate",
      qualifiedByName = "ExtractReleaseDate")
  @Mapping(target = "imageUrl", source = "mastheadScreenshot", qualifiedByName = "ExtractImageUrl")
  @Mapping(
      target = "verticalImageUrl",
      source = "verticalLogoScreenshot",
      qualifiedByName = "ExtractImageUrl")
  @Mapping(target = "openCriticUrl", source = "url", qualifiedByName = "StringToUrl")
  Game toGame(GameResponse response);

  // Qualified by name methods
  @Named("DoubleToInt")
  default int castDoubleToInt(double averageScore) {
    return (int) Math.round(averageScore);
  }

  @Named("ExtractReviewSummary")
  default String extractSummary(ReviewSummary reviewSummary) {
    return reviewSummary.getSummary();
  }

  @Named("ExtractDeveloper")
  default String extractDeveloper(List<Company> companies) {
    Company company =
        companies.stream()
            .filter(c -> c.getType().equals(Company.Type.DEVELOPER))
            .findFirst()
            .orElse(null);
    if (company == null) {
      return extractPublisher(companies);
    }

    return company.getName();
  }

  @Named("ExtractPublisher")
  default String extractPublisher(List<Company> companies) {
    Company company =
        companies.stream()
            .filter(c -> c.getType().equals(Company.Type.PUBLISHER))
            .findFirst()
            .orElse(null);
    if (company == null) {
      return extractDeveloper(companies);
    }

    return company.getName();
  }

  @Named("ExtractReleaseYear")
  default String getReleaseYear(String firstReleaseDate) {
    return firstReleaseDate.substring(0, 4);
  }

  @Named("ExtractReleaseDate")
  default String getReleaseDate(String firstReleaseDate) {
    String[] suffixes = {
      "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
      "th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
      "th", "st"
    };

    Instant instant = Instant.parse(firstReleaseDate);
    LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

    return date.getDayOfMonth()
        + suffixes[date.getDayOfMonth()]
        + " "
        + date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
  }

  @Named("ExtractImageUrl")
  default URL extractImageUrl(Screenshot screenshot) throws MalformedURLException {
    if (screenshot == null) {
      return null;
    }

    return new URL("https:" + screenshot.getFullRes());
  }

  @Named("StringToUrl")
  default URL castStringToUrl(String url) throws MalformedURLException {
    if (url == null) {
      return null;
    }

    return new URL(url);
  }
}
