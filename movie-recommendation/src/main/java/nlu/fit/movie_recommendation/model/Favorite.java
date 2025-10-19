package nlu.fit.movie_recommendation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity()
@Table(name = "favorite")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
}
