package nlu.fit.movie_backend.viewmodel.movie;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record SeasonVm(
        Long id,
        int seasonNumber,
        LocalDate airDate,
        List<EpisodeVm> episodeVmList
) {
}
