package nlu.fit.movie_backend.viewmodel.user;

import lombok.Builder;

import java.util.List;

@Builder
public record OnboardingPostVm(List<Long> genres) {
}
