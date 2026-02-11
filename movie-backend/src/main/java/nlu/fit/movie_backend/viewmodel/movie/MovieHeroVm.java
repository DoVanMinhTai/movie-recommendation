package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieHeroVm(
        Long id,
        String title,
        String description,
        String backdrop_url,
        String overview,
        String trailerKey
) {
}
