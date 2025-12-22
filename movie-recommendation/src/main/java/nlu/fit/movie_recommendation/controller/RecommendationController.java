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

    @PostMapping("/getRecommendationById")
    public ResponseEntity<List<MovieRecommendationVm>> getRecommendationsById(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsById(userId));
    }

    /*
     * WorkFlow for call API RecommendationService.updateRecommendations()
     * Input - Logic - Output
     * */
    @PostMapping("/update")
    public ResponseEntity<?> updateRecommendations() {
        return ResponseEntity.ok(recommendationService.updateRecommendations());
    }


}
