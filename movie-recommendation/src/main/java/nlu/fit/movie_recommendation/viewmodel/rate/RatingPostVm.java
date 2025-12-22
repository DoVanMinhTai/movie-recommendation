package nlu.fit.movie_recommendation.viewmodel.rate;

public record RatingPostVm(
        Double score, String comment, Long movieId, Long userId
) {
}
