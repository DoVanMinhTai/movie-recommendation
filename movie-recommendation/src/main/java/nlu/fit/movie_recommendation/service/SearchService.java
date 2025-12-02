package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.document.MovieDocument;
import nlu.fit.movie_recommendation.repository.elasticsearchrepository.MovieSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final MovieSearchRepository movieSearchRepository;

    /*
     * Find movies by genre
     * check exist elastic search index
     *   check genre
     *       if exist get movies from index
     *       else getAllMovies()
     * if not exist create index
     *   check genre then get JPA movies or not
     * */
//    public Page<MovieSearchVm> searchMovies(String genre, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        try {
//            if (genre != null) {
//                Page<MovieDocument> docs = searchService.findByGenre(genre, pageable);
//                return convertMovieDocumentToMovieSearchVm(docs);
//            } else {
//                Page<MovieDocument> docs = searchService.findAll(pageable);
//                return convertMovieDocumentToMovieSearchVm(docs);
//            }
//        } catch (Exception e) {
//            log.error("ElasticSearch unavailable, falling back to JPA", e);
//        }
//        if (genre != null && !genre.isEmpty()) {

    /// /            Page<Movie> moviePage = movieRepository.findByGenre(genre, pageable);
    /// /            return convertMovieToMovieSearchVm(moviePage);
//            return null;
//        } else {
//            Page<Movie> moviePage = movieRepository.findAll(pageable);
//            return convertMovieToMovieSearchVm(moviePage);
//        }
//    }


    public Page<MovieDocument> findByGenre(String genre, Pageable pageable) {
        return movieSearchRepository.findByGenre(genre, pageable);
    }

    public Page<MovieDocument> findAll(Pageable pageable) {
        return movieSearchRepository.findAll(pageable);
    }
}
