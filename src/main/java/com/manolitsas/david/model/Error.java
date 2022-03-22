package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.OffsetDateTime;

@Validated
@Builder
@Data
public class Error {

    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("error")
    private String error;

    @JsonProperty("message")
    private String message;
}
