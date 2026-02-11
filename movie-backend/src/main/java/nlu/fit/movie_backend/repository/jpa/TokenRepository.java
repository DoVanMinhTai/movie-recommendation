package nlu.fit.movie_backend.repository.jpa;

import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.model.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<UserToken,Long> {
}
