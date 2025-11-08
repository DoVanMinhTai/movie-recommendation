package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.repository.elasticsearchrepository.MovieSearchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SearchService {
    private final MovieSearchRepository movieSearchRepository;

    public Page<Movie> findByGenre(String genre, Pageable pageable) {
        return movieSearchRepository.findByGenre(genre, pageable);
    }

    public Page<Movie> findAll() {
        return movieSearchRepository.findAll(Pageable.unpaged());
    }
}
