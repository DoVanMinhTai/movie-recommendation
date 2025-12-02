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

    /* Should I create API as below for Project?
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
    */
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


}
