package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "movies")
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

    private String genre;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Rating> rating;

}
