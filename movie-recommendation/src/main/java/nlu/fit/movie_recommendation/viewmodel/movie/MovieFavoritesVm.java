package nlu.fit.movie_recommendation.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieFavoritesVm(
        Long id, String title
) {
}
