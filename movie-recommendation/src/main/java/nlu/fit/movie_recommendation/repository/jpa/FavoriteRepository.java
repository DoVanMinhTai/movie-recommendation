package nlu.fit.movie_recommendation.repository.jpa;

import nlu.fit.movie_recommendation.model.Favorite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findAllByUserId(Long userId, Pageable pageable);

    Favorite findByUserIdAndMovieId(Long userId, Long movieId);
}
