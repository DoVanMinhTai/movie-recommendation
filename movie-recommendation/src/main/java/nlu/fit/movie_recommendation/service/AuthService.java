package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.AuthRepository;
import nlu.fit.movie_recommendation.viewmodel.auth.LoginVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterPostVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public RegisterVm register(RegisterPostVm request) {
        User user = new User();
        user.setUserName(request.userName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user = authRepository.save(user);
        return RegisterVm.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail()).build();
    }

    public ProfileVm login(LoginVm loginRequest) {
        String userName = loginRequest.userName();
        String password = loginRequest.password();
        var authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        userName,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = authRepository.findByUserName(userName);

        return ProfileVm.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

    public ProfileVm getProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        if (principal instanceof User user) {
            return ProfileVm.builder()
                    .id(user.getId())
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .build();
        } else {
            return null;
        }

    }
}
