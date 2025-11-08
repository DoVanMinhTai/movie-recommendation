package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.repository.RateRepository;
import nlu.fit.movie_recommendation.viewmodel.rate.RatingPostVm;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;

    public void rateMovie(RatingPostVm ratingRequest) {

    }

    /*?*/
    public Double getAverageRating(Long movieId) {
        return null;
    }
}
