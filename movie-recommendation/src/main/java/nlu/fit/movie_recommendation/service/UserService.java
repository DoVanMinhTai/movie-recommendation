package nlu.fit.movie_recommendation.service;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.UserRepository;
import nlu.fit.movie_recommendation.viewmodel.movie.MovieFavoritesVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import nlu.fit.movie_recommendation.viewmodel.user.UserPutVm;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ProfileVm getUserById(Long id) {
        User user = userRepository.findById(id).get();
        return ProfileVm.builder().build();
    }

    public UserPutVm updateUser(Long id, UserPutVm user) {
        return null;
    }

    public MovieFavoritesVm getAllMovieFavorites(Long id) {
        return null;
    }

    public void addFavorite(Long id, Long movieId) {

    }

    public void deleteFavorite(Long id, Long movieId) {
    }
}
