package com.manolitsas.david.mapper;

import com.manolitsas.david.client.model.OpenCriticGameResponse;
import com.manolitsas.david.model.Game;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GameMapper {

    @Mapping(target = "name", source = "response.name")
    Game toGame(OpenCriticGameResponse response);

}
