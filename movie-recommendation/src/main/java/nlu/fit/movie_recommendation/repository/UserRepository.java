package nlu.fit.movie_recommendation.repository;

import nlu.fit.movie_recommendation.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User,Long> {
}
