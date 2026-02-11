package nlu.fit.movie_backend.viewmodel.auth;

public record RegisterPostVm(
        String userName,
        String email,
        String password
) {
}
