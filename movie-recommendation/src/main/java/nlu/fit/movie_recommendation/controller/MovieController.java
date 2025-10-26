package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.MovieService;
import nlu.fit.movie_recommendation.viewmodel.movie.MoviePostVm;
import nlu.fit.movie_recommendation.viewmodel.movie.MoviePutVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/")
    public ResponseEntity<?> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "0") int size,
            @RequestParam(required = false) String genre
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    /*
     ** API Admin
     */

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody MoviePostVm movieRequest) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/putMovie")
    public ResponseEntity<?> updateMovie(@RequestBody MoviePutVm request) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }


}
