package nlu.fit.movie_recommendation.service;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.model.Favorite;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.jpa.FavoriteRepository;
import nlu.fit.movie_recommendation.repository.jpa.MovieRepository;
import nlu.fit.movie_recommendation.repository.jpa.UserRepository;
import nlu.fit.movie_recommendation.viewmodel.movie.MovieFavoritesVm;
import nlu.fit.movie_recommendation.viewmodel.user.ProfileVm;
import nlu.fit.movie_recommendation.viewmodel.user.UserPutVm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;

    public ProfileVm getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        return ProfileVm.builder().build();
    }

    /* Do I need this?
    public UserPutVm updateUser(Long id, UserPutVm user) {
        User userUpdated = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        userUpdated.set

        return null;
    }
    */

    public List<MovieFavoritesVm> getAllMovieFavorites(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        /*
        * Check user or ...
        * */
        if(user == null) return null;

        List<Favorite> favorites = favoriteRepository.findAllByUserId(user.getId(), Pageable.unpaged());
        return favorites.stream().map(item -> new MovieFavoritesVm(item.getId(),item.getMovie().getTitle())).toList();
    }

    public Void addFavorite(Long id, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new RuntimeException("Movie not found")
        );

        Favorite favorite = new Favorite();
        favorite.setMovie(movie);
        favorite.setUser(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        ));
        favoriteRepository.save(favorite);
        return null;
    }

    public Void deleteFavorite(Long id, Long movieId) {
        Favorite favorite = favoriteRepository.findByUserIdAndMovieId(id, movieId);
        favoriteRepository.delete(favorite);
        return null;
    }
}
