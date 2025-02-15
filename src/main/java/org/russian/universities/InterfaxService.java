package org.russian.universities;

import org.russian.universities.dto.interfax.InterfaxUniversitiesResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class InterfaxService {
    private final WebClient webClient;

    public InterfaxService(WebClient webClient){
        this.webClient = webClient;
    }

    public InterfaxUniversitiesResponse getSummaryResponse() {
        var pagesResult = fetchAllPages();
        return new InterfaxUniversitiesResponse(
                pagesResult.stream().flatMap(it -> it.interfaxUniversities().stream()).toList(),
                pagesResult.getFirst().year(),
                pagesResult.getFirst().count()
        );
    }

    private List<InterfaxUniversitiesResponse> fetchAllPages() {
        var firstPageResult = fetchPage(1);

        return IntStream
                .rangeClosed(1, (firstPageResult.count() / firstPageResult.interfaxUniversities().size()) + 1)
                .mapToObj(this::fetchPage)
                .toList();
    }

    private InterfaxUniversitiesResponse fetchPage(int page) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/rating/")
                        .queryParam("page", page)
                        .queryParam("rating", 1)
                        .queryParam("year", LocalDate.now().getYear() - 1)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(InterfaxUniversitiesResponse.class).block();
    }
}
