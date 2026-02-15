package nlu.fit.movie_backend.viewmodel.admin;

public record ActiveModelDto(
        String name, Double version, Double rmse,
        Double mae, Double f1Score, String path
) {
}
