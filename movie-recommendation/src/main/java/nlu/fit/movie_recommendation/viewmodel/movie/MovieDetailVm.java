package nlu.fit.movie_recommendation.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieDetailVm(
        Long id, String genre
) {
}
