package nlu.fit.movie_recommendation.repository.elasticsearchrepository;

import nlu.fit.movie_recommendation.document.MovieDocument;
import nlu.fit.movie_recommendation.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieSearchRepository extends ElasticsearchRepository<MovieDocument,String> {
    Page<MovieDocument> findByGenre(String genre, Pageable pageable);
}

