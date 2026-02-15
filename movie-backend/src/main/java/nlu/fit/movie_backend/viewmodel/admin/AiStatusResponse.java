package nlu.fit.movie_backend.viewmodel.admin;

import java.util.List;

public record AiStatusResponse(
        ActiveModelDto activeModel,
        List<JobLogDto> recentJobs,
        JobLogDto currentJob
) {
}
