package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.service.AuthService;
import nlu.fit.movie_recommendation.service.UserService;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
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
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {

    }

    @GetMapping("/{id}/getFavorites")
    public ResponseEntity<?> getFavorites(@PathVariable Long id) {

    }

    @PostMapping("/{id}/getFavorites")
    public ResponseEntity<?> addFavorite(@PathVariable Long id, @RequestBody Long movieId) {

    }

    @DeleteMapping("/{id}/favorites/{movieId}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id, @PathVariable Long movieId) {
        return ResponseEntity.ok().build();
    }


}
