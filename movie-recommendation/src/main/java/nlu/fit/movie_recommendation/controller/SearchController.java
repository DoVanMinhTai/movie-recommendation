package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.document.MovieDocument;
import nlu.fit.movie_recommendation.service.SearchService;
import nlu.fit.movie_recommendation.viewmodel.search.MovieSearchVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private SearchService searchService;

    /*
    https://gemini.google.com/app/b4417b451094a312
     */
    @GetMapping("")
    public ResponseEntity<List<MovieDocument>> searchMovies(@RequestParam() String title) {
        return ResponseEntity.ok(searchService.findByTitleContaining(title));
    }

    @GetMapping("/genre")
    public ResponseEntity<Page<MovieSearchVm>> searchMoviesByGenre(
            @RequestParam("genre") String genre,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(searchService.findByGenre(genre, page, size));
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> suggestMovies(@RequestParam("prefix") String prefix) {
        return ResponseEntity.ok().build();
    }
}
