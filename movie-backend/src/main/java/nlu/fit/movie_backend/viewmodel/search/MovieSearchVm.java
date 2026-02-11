package nlu.fit.movie_backend.viewmodel.search;

import lombok.Builder;
import nlu.fit.movie_backend.document.MediaContentDocument;

@Builder
public record MovieSearchVm(
        String id, String title
) {
    public static MovieSearchVm fromMovieDocumentToMovieSearchVm(MediaContentDocument mediaContentDocument) {
//        return MovieSearchVm.builder().id(movieDocument.getId())
//                .title(movieDocument.getTitle()).build();
        return null;
    }
}
