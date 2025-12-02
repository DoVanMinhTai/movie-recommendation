package nlu.fit.movie_recommendation.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieThumbnailVms(
        Long id, String title
) {
}
