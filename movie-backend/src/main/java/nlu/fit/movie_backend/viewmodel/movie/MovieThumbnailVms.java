package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieThumbnailVms(
        Long id, String title, String backdropPath
) {
}
