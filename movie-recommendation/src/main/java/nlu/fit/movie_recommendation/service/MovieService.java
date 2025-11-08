package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.repository.MovieRepository;
import nlu.fit.movie_recommendation.viewmodel.movie.MovieDetailVm;
import nlu.fit.movie_recommendation.viewmodel.movie.MovieSearchVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final SearchService searchService;

    /*
     * Find movies by genre
     * check exist elastic search index
     *   check genre
     *       if exist get movies from index
     *       else getAllMovies()
     * if not exist create index
     *   check genre then get JPA movies or not
     * */

    public Page<MovieSearchVm> searchMovies(String genre, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (searchService != null) {

            if (genre != null) {
                if (!searchService.findByGenre(genre, pageable).hasContent()) {

                }
                Page<Movie> moviePage = searchService.findByGenre(genre, pageable);
                return convertMovieToMovieSearchVm(moviePage);
            } else {
                Page<Movie> moviePageAll = searchService.findAll();
                return convertMovieToMovieSearchVm(moviePageAll);

            }
        } else {
            if (genre != null && !genre.isEmpty()) {
                Page<Movie> moviePage = movieRepository.findAll(pageable);
                return convertMovieToMovieSearchVm(moviePage);
            } else {
                Page<Movie> moviePage = movieRepository.findAll(pageable);
                return convertMovieToMovieSearchVm(moviePage);
            }
        }
    }

    public MovieDetailVm getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertMovieToMovieDetailVm)
                .orElseThrow(() -> {
                    log.warn("Movie not found with id {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }






    /*
     * Hepler Method
     * */

    private MovieDetailVm convertMovieToMovieDetailVm(Movie movie) {
    }

    private Page<MovieSearchVm> convertMovieToMovieSearchVm(Page<Movie> moviePage) {
    }

}
