package nlu.fit.movie_recommendation.repository.jpa;

import nlu.fit.movie_recommendation.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rating, Long> {
}
