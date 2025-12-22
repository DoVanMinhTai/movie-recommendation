package nlu.fit.movie_recommendation.viewmodel.user;

import lombok.Builder;

@Builder
public record ProfileVm(
        Long id,
        String userName, String email
) {
}
