package nlu.fit.movie_backend.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.service.ChatbotService;
import nlu.fit.movie_backend.viewmodel.chatbot.ChatRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@AllArgsConstructor
public class ChatBotController {
    private final ChatbotService chatbotService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody ChatRequest chatRequest) {
        return ResponseEntity.ok(chatbotService.sendMessage(chatRequest));
    }

    @GetMapping("/history")
    public ResponseEntity<?> getMessages(@RequestParam Long userId) {
        return ResponseEntity.ok(chatbotService.getMessages(userId));
    }
}
