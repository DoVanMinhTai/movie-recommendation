package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nlu.fit.movie_recommendation.model.Genre;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.repository.jpa.GenreRepository;
import nlu.fit.movie_recommendation.repository.jpa.MovieRepository;
import nlu.fit.movie_recommendation.viewmodel.movie.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public List<MovieThumbnailVms> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return convertListMoviesToListMovieThumbnailVms(movies);
    }

    public MovieDetailVm addMovie(MoviePostVm moviePostVm) {
        Movie movie = new Movie();
        movie.setTmDBId(moviePostVm.tmDBId());
        movie.setTitle(moviePostVm.title());
        movie.setOriginalTitle(moviePostVm.originalTitle());
        movie.setOverview(moviePostVm.overview());
        movie.setReleaseDate(moviePostVm.releaseDate());
        movie.setPosterPath(moviePostVm.posterPath());
        movie.setBackdropPath(moviePostVm.backdropPath());
        movie.setRuntime(moviePostVm.runtime());
        movie.setTrailerKey(moviePostVm.trailerKey());
        movie.setVoteAverage(moviePostVm.voteAverage());
        movie.setVoteCount(moviePostVm.voteCount());
        movie.setPopularity(moviePostVm.popularity());

        List<Genre> genres = genreRepository.findAllById(moviePostVm.genresId());

        movie.setGenres(genres);
        Movie savedMovie = movieRepository.save(movie);
        return convertMovieToMovieDetailVm(savedMovie);
    }

    public MovieDetailVm putMovie(MoviePutVm request) {
        Movie movie = movieRepository.findById(request.id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        MoviePostVm moviePostVm = request.moviePostVm();
        movie.setTmDBId(moviePostVm.tmDBId());
        movie.setTitle(moviePostVm.title());
        movie.setOriginalTitle(moviePostVm.originalTitle());
        movie.setOverview(moviePostVm.overview());
        movie.setReleaseDate(moviePostVm.releaseDate());
        movie.setPosterPath(moviePostVm.posterPath());
        movie.setBackdropPath(moviePostVm.backdropPath());
        movie.setRuntime(moviePostVm.runtime());
        movie.setTrailerKey(moviePostVm.trailerKey());
        movie.setVoteAverage(moviePostVm.voteAverage());
        movie.setVoteCount(moviePostVm.voteCount());
        movie.setPopularity(moviePostVm.popularity());
        Movie movieSaved = movieRepository.save(movie);
        return convertMovieToMovieDetailVm(movieSaved);
    }

    public Void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        movie.setDeleted(true);
        movieRepository.save(movie);
        return null;
    }

    public MovieDetailVm getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(this::convertMovieToMovieDetailVm)
                .orElseThrow(() -> {
                    log.warn("Movie not found with id {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });
    }

    private MovieDetailVm convertMovieToMovieDetailVm(Movie movie) {
        return new MovieDetailVm(movie.getId(), movie.getTitle());
    }

    private List<MovieThumbnailVms> convertListMoviesToListMovieThumbnailVms(List<Movie> movies) {
        return movies.stream().map(item -> new MovieThumbnailVms(item.getId(), item.getTitle())).toList();
    }
}
