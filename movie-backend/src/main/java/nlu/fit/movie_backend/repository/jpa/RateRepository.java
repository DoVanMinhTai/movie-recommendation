package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.Rating;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rating, Long> {
//    List<Rating> findAllByMovieId(Long movieId, Pageable pageable);
}
