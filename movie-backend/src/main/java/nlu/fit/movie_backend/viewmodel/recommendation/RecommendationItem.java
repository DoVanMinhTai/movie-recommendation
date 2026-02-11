package nlu.fit.movie_backend.viewmodel.recommendation;

public record RecommendationItem(
        Long movie_id, Double score
) {
}
