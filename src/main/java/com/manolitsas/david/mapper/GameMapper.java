package com.manolitsas.david.mapper;

import com.manolitsas.david.client.model.Company;
import com.manolitsas.david.client.model.MastheadScreenshot;
import com.manolitsas.david.client.model.OpenCriticGameResponse;
import com.manolitsas.david.client.model.ReviewSummary;
import com.manolitsas.david.model.Game;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
  @Mapping(target = "imageUrl", source = "mastheadScreenshot", qualifiedByName = "ExtractImageUrl")
  Game toGame(OpenCriticGameResponse response);

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

  @Named("ExtractImageUrl")
  default URL extractImageUrl(MastheadScreenshot mastheadScreenshot) throws MalformedURLException {
    if (mastheadScreenshot == null) {
      return null;
    }

    return new URL("https:" + mastheadScreenshot.getFullRes());
  }
}
