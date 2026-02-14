package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    Set<Genre> findAllByIdIn(List<Long> ids);
}
