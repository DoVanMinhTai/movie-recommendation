package nlu.fit.movie_recommendation.viewmodel.search;

import lombok.Builder;
import nlu.fit.movie_recommendation.document.MovieDocument;

@Builder
public record MovieSearchVm(
        String id, String title
) {
    public MovieSearchVm fromMovieDocumentToMovieSearchVm(MovieDocument movieDocument) {
        return MovieSearchVm.builder().id(movieDocument.getId())
                .title(movieDocument.getTitle());
    }
}
