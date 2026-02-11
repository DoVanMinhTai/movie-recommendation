package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

@Builder
public record SimilarMovieVms(
        Long id, String title, String backdropPath
) {
}
