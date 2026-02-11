package nlu.fit.movie_backend.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.service.AdminService;
import nlu.fit.movie_backend.service.MovieService;
import nlu.fit.movie_backend.service.UserService;
import nlu.fit.movie_backend.viewmodel.admin.AdminStatsResponse;
import nlu.fit.movie_backend.viewmodel.admin.UserResponse;
import nlu.fit.movie_backend.viewmodel.movie.MoviePostVm;
import nlu.fit.movie_backend.viewmodel.movie.MoviePutVm;
import nlu.fit.movie_backend.viewmodel.movie.MovieThumbnailVms;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final MovieService movieService;
    private final UserService userService;
    private final AdminService adminService;

    @GetMapping("/statistics")
    public ResponseEntity<AdminStatsResponse> getStatistics() {
        return ResponseEntity.ok(adminService.getStatistics());
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieThumbnailVms>> getAllMoviesForAdmin() {
        return ResponseEntity.ok(movieService.getAllMovies());
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
