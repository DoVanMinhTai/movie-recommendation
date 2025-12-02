package nlu.fit.movie_recommendation.config;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.jpa.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class CustomUserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findByUserName(username);
        org.springframework.security.core.userdetails.User result = new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                List.of()
        );
        return result;
    }
}
