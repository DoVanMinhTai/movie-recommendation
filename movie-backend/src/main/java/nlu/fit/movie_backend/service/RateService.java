package nlu.fit.movie_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import nlu.fit.movie_backend.model.MediaContent;
import nlu.fit.movie_backend.model.Movie;
import nlu.fit.movie_backend.model.Rating;
import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.repository.jpa.MediaContentRepository;
import nlu.fit.movie_backend.repository.jpa.MovieRepository;
import nlu.fit.movie_backend.repository.jpa.RateRepository;
import nlu.fit.movie_backend.repository.jpa.UserRepository;
import nlu.fit.movie_backend.viewmodel.rate.RatingPostVm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepository rateRepository;
    private final UserRepository userRepository;
    private final MediaContentRepository mediaContentRepository;

    @Transactional
    public Rating rateMovie(Long userId, RatingPostVm ratingRequest) {
        Rating rating = rateRepository.findByUserIdAndMediaContentId(userId, ratingRequest.movieId())
                .orElse(new Rating());

        MediaContent mediaContent = mediaContentRepository.findById(ratingRequest.movieId())
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));

        rating.setScore(ratingRequest.score());
        rating.setComment(ratingRequest.comment());
        rating.setMediaContent(mediaContent);
        rating.setUser(user);

        return rateRepository.save(rating);
    }

    public Double getAverageRating(Long movieId) {
        Pageable pageable = PageRequest.of(0, 1);
//        List<Rating> rates = rateRepository.findAllByMovieId(movieId, pageable);
//        return rates.stream().mapToDouble(Rating::getScore).average().orElse(0.0);
        return null;
    }
}
