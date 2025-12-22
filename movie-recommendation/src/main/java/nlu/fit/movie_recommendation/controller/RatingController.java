package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.RateService;
import nlu.fit.movie_recommendation.viewmodel.rate.RatingPostVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
@AllArgsConstructor
public class RatingController {
    private final RateService rateService;

    @PostMapping("")
    public ResponseEntity<Void> rateMovie(@RequestBody RatingPostVm ratingRequest) {
        return ResponseEntity.ok(rateService.rateMovie(ratingRequest));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long movieId) {
        return ResponseEntity.ok().build();
    }

}
