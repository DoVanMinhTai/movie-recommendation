package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity()
@Table(name = "seasons")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int seasonNumber;

    private LocalDate airDate;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    @OneToMany(mappedBy = "seasons", cascade = CascadeType.ALL)
    private List<Episode> episodes;

}
