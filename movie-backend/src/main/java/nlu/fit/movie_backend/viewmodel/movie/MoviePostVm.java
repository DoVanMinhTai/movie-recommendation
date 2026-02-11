package nlu.fit.movie_backend.viewmodel.movie;

import java.time.LocalDateTime;
import java.util.List;

public record MoviePostVm(
        Long tmDBId, String title, String originalTitle,
        List<Long> genresId, String overview, LocalDateTime releaseDate,
        String posterPath, String backdropPath, int runtime, String trailerKey,
        Double voteAverage, int voteCount, Double popularity
) {
}
