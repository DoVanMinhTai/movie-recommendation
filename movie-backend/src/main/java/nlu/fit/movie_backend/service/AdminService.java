package nlu.fit.movie_backend.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_backend.model.Movie;
import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.repository.jpa.MovieRepository;
import nlu.fit.movie_backend.repository.jpa.UserRepository;
import nlu.fit.movie_backend.viewmodel.admin.AdminStatsResponse;
import nlu.fit.movie_backend.viewmodel.admin.MovieResponse;
import nlu.fit.movie_backend.viewmodel.admin.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    /*
     * Return: Total user, view count,...
     * */
    public AdminStatsResponse getStatistics() {
        return null;
    }

    public List<UserResponse> getAllUsers() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> users = userRepository.findAll(pageable);
        return users.stream().map(user -> new UserResponse(user.getId(), user.getEmail(), user.getEmail())).toList();
    }

}
