package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.MovieService;
import nlu.fit.movie_recommendation.viewmodel.admin.AdminStatsResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.MovieResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.UserResposne;
import nlu.fit.movie_recommendation.viewmodel.movie.MoviePostVm;
import nlu.fit.movie_recommendation.viewmodel.movie.MoviePutVm;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private MovieService movieService;

    @GetMapping("/statistics")
    public ResponseEntity<AdminStatsResponse> getStatistics() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResposne>> getAllUsers() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getAllMoviesForAdmin() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody @Validated MoviePostVm movieRequest) {
        return ResponseEntity.ok(movieService.addMovie(movieRequest));
    }

    @PutMapping("/putMovie")
    public ResponseEntity<?> updateMovie(@RequestBody @Validated MoviePutVm request) {
        return ResponseEntity.ok(movieService.putMovie(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.deleteMovie(id));
    }
}
