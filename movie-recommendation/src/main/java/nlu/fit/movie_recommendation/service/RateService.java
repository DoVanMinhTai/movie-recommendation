package nlu.fit.movie_recommendation.service;

import lombok.RequiredArgsConstructor;
import nlu.fit.movie_recommendation.model.Movie;
import nlu.fit.movie_recommendation.model.Rating;
import nlu.fit.movie_recommendation.model.User;
import nlu.fit.movie_recommendation.repository.jpa.MovieRepository;
import nlu.fit.movie_recommendation.repository.jpa.RateRepository;
import nlu.fit.movie_recommendation.repository.jpa.UserRepository;
import nlu.fit.movie_recommendation.viewmodel.rate.RatingPostVm;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public Void rateMovie(RatingPostVm ratingRequest) {
        Rating rating = new Rating();
        rating.setScore(ratingRequest.score());
        rating.setComment(ratingRequest.comment());

        Movie movie = movieRepository.findById(ratingRequest.movieId()).orElseThrow();
        rating.setMovie(movie);

        User user = userRepository.findById(ratingRequest.userId()).orElseThrow();
        rating.setUser(user);
        rateRepository.save(rating);
        return null;
    }

    /*?*/
    public Double getAverageRating(Long movieId) {

        return null;
    }
}
