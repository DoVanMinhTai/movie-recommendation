package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity()
@Table(name = "episodes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true)
    private Integer seasonNumber;

    @Column(nullable = true)
    private Integer episodeNumber;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String overview;

    private String videoUrl;

    private String stillPath;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season seasons;
}
