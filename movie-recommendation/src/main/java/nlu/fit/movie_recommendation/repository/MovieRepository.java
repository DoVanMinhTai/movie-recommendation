package nlu.fit.movie_recommendation.repository;

import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

}
