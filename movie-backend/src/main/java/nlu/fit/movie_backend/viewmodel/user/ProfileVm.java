package nlu.fit.movie_backend.viewmodel.user;

import lombok.Builder;
import nlu.fit.movie_backend.model.enumeration.ROLE;

@Builder
public record ProfileVm(
        Long id,
        String userName, String email, ROLE role, String token
) {
}
