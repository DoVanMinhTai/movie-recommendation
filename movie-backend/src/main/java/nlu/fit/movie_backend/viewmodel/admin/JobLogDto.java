package nlu.fit.movie_backend.viewmodel.admin;

public record JobLogDto(
        String id, String status, String createdAt, String errorMessage,Double contentBasedTime,
        Double collaboratingTime
) {
}
