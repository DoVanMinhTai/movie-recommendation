package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
@AllArgsConstructor
public class RatingController {
    private final RateService rateService;

    @PostMapping("")
    public ResponseEntity<?> rateMovie(@RequestBody RatingRequest ratingRequest) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long movieId) {
        return ResponseEntity.ok().build();
    }


}
