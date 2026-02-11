package nlu.fit.movie_backend.viewmodel.recommendation;

import lombok.Builder;

import java.util.List;

@Builder
public record UserContext(
        Long user_Id,
        List<String> selected_Genres,
        boolean is_new_user
) {
}
