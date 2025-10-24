package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.UserService;
import nlu.fit.movie_recommendation.viewmodel.movie.MovieFavoritesVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import nlu.fit.movie_recommendation.viewmodel.user.UserPutVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        ProfileVm profileResponse = userService.getUserById(id);
        return ResponseEntity.ok(profileResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserPutVm user) {
        UserPutVm userPutVm = userService.updateUser(id, user);
        return ResponseEntity.ok(userPutVm);

    }

    @GetMapping("/{id}/getFavorites")
    public ResponseEntity<?> getFavorites(@PathVariable Long id) {
        MovieFavoritesVm movieFavoritesVm = userService.getAllMovieFavorites(id);
        return ResponseEntity.ok(movieFavoritesVm);
    }

    @PostMapping("/{id}/getFavorites")
    public ResponseEntity<?> addFavorite(@PathVariable Long id, @RequestBody Long movieId) {
        userService.addFavorite(id,movieId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/favorites/{movieId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id, @PathVariable Long movieId) {
        userService.deleteFavorite(id,movieId);
        return ResponseEntity.ok().build();
    }


}
