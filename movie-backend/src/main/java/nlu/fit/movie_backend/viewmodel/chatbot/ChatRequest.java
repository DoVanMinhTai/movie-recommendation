package nlu.fit.movie_backend.viewmodel.chatbot;

import java.util.List;

public record ChatRequest(
        Long userId, String message, List<HistoryMessage> historyMessageList
) {
}
