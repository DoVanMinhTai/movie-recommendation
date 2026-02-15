package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "training_jobs_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingJobsLogs {

    @Id
    private String id;

    @Column(name = "job_status")
    private String jobStatus;

    @Column(name = "content_based_time")
    private Double contentBasedTime;

    @Column(name = "colleborating_time")
    private Double collaboratingTime;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}