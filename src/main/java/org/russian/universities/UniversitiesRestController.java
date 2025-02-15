package org.russian.universities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.russian.universities.dto.interfax.InterfaxUniversitiesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/universities")
public class UniversitiesRestController {
    private final InterfaxService interfaxService;
    private final ObjectMapper objectMapper;

    public UniversitiesRestController(InterfaxService interfaxService, ObjectMapper objectMapper){
        this.interfaxService = interfaxService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/interfax")
    public InterfaxUniversitiesResponse fetchAllInterfaxUniversities() {
        return interfaxService.getSummaryResponse();
    }

    @GetMapping("/interfax/save")
    public String fetchAllInterfaxUniversitiesAndSave() {
        var response = interfaxService.getSummaryResponse();
        try {
            var file = new File("./interfax.json");
            objectMapper.writeValue(file, response);
            return file.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
