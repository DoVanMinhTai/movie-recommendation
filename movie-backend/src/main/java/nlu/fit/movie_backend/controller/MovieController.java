package nlu.fit.movie_backend.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.model.Genre;
import nlu.fit.movie_backend.model.enumeration.CONTENTTYPE;
import nlu.fit.movie_backend.service.MovieService;
import nlu.fit.movie_backend.viewmodel.movie.MediaContentVm;
import nlu.fit.movie_backend.viewmodel.movie.MovieHeroVm;
import nlu.fit.movie_backend.viewmodel.movie.MovieThumbnailVms;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<?> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Page<MediaContentVm>> getMovieById(@PathVariable Long movieId) {
        return ResponseEntity.ok(movieService.getMovieById(movieId));
    }

    @GetMapping("/movies/latest")
    public ResponseEntity<List<MovieThumbnailVms>> getLatestMovies(
            @RequestParam int page, @RequestParam int size
    ) {
        return ResponseEntity.ok(movieService.getLatestMovies(page, size));
    }

    @GetMapping("/movies/trending")
    public ResponseEntity<List<MovieThumbnailVms>> getMovieTrending(
            @RequestParam int limit
    ) {
        return ResponseEntity.ok(movieService.getMovieTrending(limit));
    }

    @GetMapping("/movies/top10")
    public ResponseEntity<List<MovieThumbnailVms>> getMovieTop10(
            @RequestParam CONTENTTYPE contenttype,
            @RequestParam int limit
    ) {
        return ResponseEntity.ok(movieService.getTop10(contenttype, limit));
    }

    @GetMapping("/movies/genres")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(movieService.getAllGenres());
    }

    @GetMapping("/movies/")
    public ResponseEntity<Page<MovieThumbnailVms>> getMoviesFilter(
            @RequestParam(name = "sortBy") String sortBy,
            @RequestParam(name = "genre", required = false) String genreId
    ) {
        return ResponseEntity.ok(movieService.filterMovies(sortBy, genreId));
    }

    @GetMapping("/movies/hero")
    public ResponseEntity<MovieHeroVm> getMovieHero(
    ) {
        return ResponseEntity.ok(movieService.getMovieHero());
    }
}
