package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity()
@Table(name = "ratings")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private List<Movie> movie;
    
}
