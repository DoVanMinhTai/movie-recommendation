package nlu.fit.movie_backend.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.model.UserToken;
import nlu.fit.movie_backend.repository.jpa.AuthRepository;
import nlu.fit.movie_backend.repository.jpa.TokenRepository;
import nlu.fit.movie_backend.repository.jpa.UserRepository;
import nlu.fit.movie_backend.viewmodel.auth.LoginVm;
import nlu.fit.movie_backend.viewmodel.auth.RegisterPostVm;
import nlu.fit.movie_backend.viewmodel.auth.RegisterVm;
import nlu.fit.movie_backend.viewmodel.user.ProfileVm;
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
    private final JWTService JwtService;
    private final TokenRepository tokenRepository;

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
        String email = loginRequest.email();
        String password = loginRequest.password();
        var authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                        email,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = authRepository.findByEmail(email);

        String jwt = JwtService.generateJWTToken(user);
        UserToken token = new UserToken();
        token.setToken(jwt);
        token.setUser(user);
        token.setRevoked(false);
        tokenRepository.save(token);
        return ProfileVm.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwt)
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

    public void logout() {
    }
}
