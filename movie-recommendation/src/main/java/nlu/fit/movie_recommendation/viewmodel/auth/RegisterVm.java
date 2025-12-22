package nlu.fit.movie_recommendation.viewmodel.auth;

import lombok.Builder;

@Builder
public record RegisterVm(
        Long id,
        String userName,
        String email
) {
}
