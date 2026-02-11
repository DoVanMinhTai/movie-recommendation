package nlu.fit.movie_backend.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.model.Rating;
import nlu.fit.movie_backend.service.RateService;
import nlu.fit.movie_backend.service.UserService;
import nlu.fit.movie_backend.viewmodel.rate.RatingPostVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserInteractionController {
    private final UserService userService;
    private final RateService rateService;

    @GetMapping("/{id}/getFavorites")
    public ResponseEntity<?> getFavorites(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getAllMovieFavorites(id));
    }

    @PostMapping("/{id}/getFavorites")
    public ResponseEntity<Void> addFavorite(@PathVariable Long id, @RequestBody Long movieId) {
        return ResponseEntity.ok(userService.addFavorite(id, movieId));
    }

    @DeleteMapping("/{id}/favorites/{movieId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id, @PathVariable Long movieId) {
        return ResponseEntity.ok(userService.deleteFavorite(id, movieId));
    }

    /*
    * Rate Movie Body {movieId, rating}
    * Add a separate API for commenting on movies
    * */
    @PostMapping("")
    public ResponseEntity<Rating> rateMovie(@RequestBody RatingPostVm ratingRequest) {
        return ResponseEntity.ok(rateService.rateMovie(ratingRequest));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long movieId) {
        return ResponseEntity.ok(rateService.getAverageRating(movieId));
    }

    /*
    * Get WatchList
    * */

    /* Saved History Watch Movies
     * {movieId, duration}
     */
}
