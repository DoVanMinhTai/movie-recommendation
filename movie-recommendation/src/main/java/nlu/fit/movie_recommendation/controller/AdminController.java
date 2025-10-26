package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nlu.fit.movie_recommendation.service.AdminService;
import nlu.fit.movie_recommendation.viewmodel.admin.AdminStatsResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.MovieResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.UserResposne;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

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


}
