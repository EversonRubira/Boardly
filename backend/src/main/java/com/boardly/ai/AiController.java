// backend/src/main/java/com/boardly/ai/AiController.java
package com.boardly.ai;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService service;

    @PostMapping("/ask")
    public ResponseEntity<AiAnswer> ask(@RequestBody AiQuestion q) {
        String text = service.ask(q.getQuestion());
        if (text.startsWith("AI error: 429")) {
            return ResponseEntity.status(429).body(new AiAnswer("Muitas requisições. Tente novamente em instantes."));
        }
        if (text.startsWith("AI error:")) {
            return ResponseEntity.status(502).body(new AiAnswer("Serviço de IA indisponível no momento."));
        }
        return ResponseEntity.ok(new AiAnswer(text));
    }
}