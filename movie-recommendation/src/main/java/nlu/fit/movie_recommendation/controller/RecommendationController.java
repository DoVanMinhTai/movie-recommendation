package nlu.fit.movie_recommendation.controller;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.service.RecommendationService;
import nlu.fit.movie_recommendation.viewmodel.recommendation.MovieRecommendationVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendation")
@AllArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping("")
    public ResponseEntity<List<MovieRecommendationVm>> getRecommendations(@PathVariable Long userId) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRecommendations() {
        return ResponseEntity.ok().build();
    }


}
