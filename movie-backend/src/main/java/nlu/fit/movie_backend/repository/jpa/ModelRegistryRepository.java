package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.ModelRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRegistryRepository extends JpaRepository<ModelRegistry, Integer> {
    Optional<ModelRegistry> findByIsActiveTrue();
}
