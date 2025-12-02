package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity()
@Table(name = "genres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @Column(name = "id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "genres",fetch = FetchType.LAZY)
    private List<Movie> movies;
    
}
