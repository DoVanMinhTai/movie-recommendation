package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

@Builder
public record MovieFavoritesVm(
        Long id, String title, String posterPath
) {
}
