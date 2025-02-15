package org.russian.universities.dto.interfax;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InterfaxUniversitiesResponse(
        @JsonProperty("universities")
        List<InterfaxUniversity> interfaxUniversities,
        int year,
        int count
) {
}
