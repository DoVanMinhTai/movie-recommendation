package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity()
@Table(name = "movies")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private Long id;

    @OneToMany(mappedBy = "movie_id", cascade = CascadeType.ALL)
    private List<Rating> rating;

}
