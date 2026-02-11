package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;
import nlu.fit.movie_backend.model.Season;

import java.util.List;

@Builder
public record SeriesDetailVm(
        Long id,
        String title,
        String backdropUrl,
        String year,
        String genres,
        String description,
        String cast,
        String director,
        Double rating,
        List<SeasonVm> seasonVm
) {
}
