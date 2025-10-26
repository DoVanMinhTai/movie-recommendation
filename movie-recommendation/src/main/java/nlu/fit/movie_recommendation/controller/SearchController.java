package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.SearchService;
import nlu.fit.movie_recommendation.viewmodel.search.MovieSearchVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/search")
@AllArgsConstructor
public class SearchController {
    private  SearchService searchService;

    @GetMapping("")
    public ResponseEntity<List<MovieSearchVm>> searchMovies(@RequestParam("q") String query) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> suggestMovies(@RequestParam("prefix") String prefix) {
        return ResponseEntity.ok().build();
    }
}
