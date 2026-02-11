package nlu.fit.movie_backend.viewmodel.recommendation;

import java.util.List;

public record UserFeedResponse(
        String strategy, String title, List<RecommendationItem> data
) {
}
