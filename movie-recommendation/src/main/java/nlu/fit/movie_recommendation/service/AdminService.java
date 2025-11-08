package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.MovieRepository;
import nlu.fit.movie_recommendation.repository.UserRepository;
import nlu.fit.movie_recommendation.viewmodel.admin.AdminStatsResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.MovieResponse;
import nlu.fit.movie_recommendation.viewmodel.admin.UserResposne;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    /*
     * API For Admin
     * */

    public AdminStatsResponse getStatistics() {
        return null;
    }

    public List<UserResposne> getAllUsers() {
        List<User> users = userRepository.findAll();
        return convertUserToUserResponse(users);
    }

    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return convertMovieToMovieResponse(movies);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
        return;
    }

    private List<MovieResponse> convertMovieToMovieResponse(List<Movie> movies) {
        return null;
    }

    private List<UserResposne> convertUserToUserResponse(List<User> user) {
        return null;
    }


}
