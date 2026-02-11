package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

import java.util.List;

@Builder
public record MediaContentVm(
        MovieDetailVm movieDetailVm,
        SeriesDetailVm seriesDetailVm,
        List<SimilarMovieVms> similarMovieVmsList
) {
}
