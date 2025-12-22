package nlu.fit.movie_recommendation.service;

import lombok.AllArgsConstructor;
import nlu.fit.movie_recommendation.config.ServiceUrlConfig;
import nlu.fit.movie_recommendation.viewmodel.recommendation.MovieRecommendationVm;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {
    private final ServiceUrlConfig serviceUrlConfig;
    private final RestClient restClient;

    public List<MovieRecommendationVm> getRecommendationsById(Long userId) {
//      check UserId

        final URI url = UriComponentsBuilder.fromHttpUrl(serviceUrlConfig.recommendation())
                .path("/recommendation/{userId}")
                .buildAndExpand(userId)
                .toUri();
        /*
        return restClient.get()
                .uri(url)
                .retrieve().body(List<MovieRecommendationVm>.class);
        */
        return null;
    }

    public String updateRecommendations() {
        final URI url = UriComponentsBuilder.fromHttpUrl(serviceUrlConfig.recommendation())
                .path("/recommendation/reTraining")
                .build().toUri();
        return null;
    }
}
