package nlu.fit.movie_backend.service;

import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.model.Favorite;
import nlu.fit.movie_backend.model.MediaContent;
import nlu.fit.movie_backend.model.Movie;
import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.repository.jpa.*;
import nlu.fit.movie_backend.viewmodel.admin.UserResponse;
import nlu.fit.movie_backend.viewmodel.movie.MovieFavoritesVm;
import nlu.fit.movie_backend.viewmodel.user.ProfileVm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;
    private final MovieRepository movieRepository;
    private final MediaContentRepository mediaContentRepository;
    private final JWTService jwtService;
    private final TokenRepository tokenRepository;

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
        if (user == null) return null;

        List<Favorite> favorites = favoriteRepository.findAllByUserId(user.getId(), Pageable.unpaged());
        return favorites.stream().map(item -> new MovieFavoritesVm(item.getId(), item.getMediaContent().getTitle())).toList();
    }

    public Void addFavorite(Long id, Long mediaContentId) {
        MediaContent mediaContent = mediaContentRepository.findById(mediaContentId).orElseThrow(
                () -> new RuntimeException("MediaContent not found")
        );

        Favorite favorite = new Favorite();
        favorite.setMediaContent(mediaContent);
        favorite.setUser(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        ));
        favoriteRepository.save(favorite);
        return null;
    }

    public Void deleteFavorite(Long id, Long movieId) {
        Favorite favorite = favoriteRepository.findByUserIdAndMediaContent_Id(id, movieId);
        favoriteRepository.delete(favorite);
        return null;
    }

    public User deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        user.setDeleted(true);
        return userRepository.save(user);
    }
}
