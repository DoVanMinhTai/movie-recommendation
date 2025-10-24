package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.AuthService;
import nlu.fit.movie_recommendation.viewmodel.auth.LoginVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterPostVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterVm> registerUser(@RequestBody RegisterPostVm registerRequest) {
        RegisterVm registerResponse = authService.register(registerRequest);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileVm> loginUser(@RequestBody LoginVm loginRequest) {
        ProfileVm loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileVm> getProfile() {
        ProfileVm profileResponse = authService.getProfile();
        return ResponseEntity.ok(profileResponse);
    }
}
