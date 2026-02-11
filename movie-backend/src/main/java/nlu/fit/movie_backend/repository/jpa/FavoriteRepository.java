package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.Favorite;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    List<Favorite> findAllByUserId(Long userId, Pageable pageable);

    Favorite findByUserIdAndMediaContent_Id(Long userId, Long mediaContentId);
}
