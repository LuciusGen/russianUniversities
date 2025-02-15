package org.russian.universities.dto.interfax;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InterfaxUniversity(
        String name,
        int point,
        String url
) {
}
