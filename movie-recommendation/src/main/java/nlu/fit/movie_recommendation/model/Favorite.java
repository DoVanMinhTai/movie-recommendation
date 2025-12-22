package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity()
@Table(name = "favorites")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @UpdateTimestamp
    private LocalDateTime createdAt;
}



