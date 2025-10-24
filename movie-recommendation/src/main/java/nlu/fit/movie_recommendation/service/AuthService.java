package nlu.fit.movie_recommendation.service;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.AuthRepository;
import nlu.fit.movie_recommendation.viewmodel.auth.LoginVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterPostVm;
import nlu.fit.movie_recommendation.viewmodel.auth.RegisterVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public RegisterVm register(RegisterPostVm request) {
//        User user = new User(request);
//        user = authRepository.save(user);
//        return new RegisterVm().builder().build();
        return null;
    }

    public ProfileVm login(LoginVm loginRequest) {
    }

    public ProfileVm getProfile() {
        return null;
    }
}
