package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.Movie;
import nlu.fit.movie_backend.viewmodel.movie.MovieThumbnailVms;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByIsDeletedFalse(boolean isDeleted, Pageable pageable);

    List<Movie> findAllByIdIn(Collection<Long> ids, Limit limit);
}
