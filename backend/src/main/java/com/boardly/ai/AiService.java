// backend/src/main/java/com/boardly/ai/AiService.java
package com.boardly.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final OpenAiClient client;
    private final String model;

    public AiService(OpenAiClient client,
                     @Value("${ai.openai.responses-model}") String model) {
        this.client = client;
        this.model = model;
    }

    public String ask(String question) {
        String system = """
    You are the internal assistant of the Boardly project (Java 17, Spring Boot, MongoDB).
    Back-end identifiers in English. Use Clean Architecture guidance.
    Be specific with file/package names and next steps.
    If info is missing, state exactly which file/class is needed.
    """;
        return client.responses(system, question);
    }
}
