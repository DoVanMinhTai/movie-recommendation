package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
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
    @Column(name = "movieId")
    private Long id;

    @Column(name = "tmdbId", unique = true)
    private Long tmDBId;

    @Column(name = "title")
    private String title;

    @Column(name = "original_title")
    private String originalTitle;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @Column(name = "overview", columnDefinition = "TEXT")
    private String overview;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @Column(name = "poster_path")
    private String posterPath;

    @Column(name = "backdrop_path")
    private String backdropPath;

    @Column(name = "runtime")
    private int runtime;

    private String trailerKey;

    @Column(name = "tmdb_vote")
    private Double voteAverage;

    @Column(name = "vote_count")
    private int voteCount;

    @Column(name = "popularity")
    private Double popularity;

    private Long movieLensId;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Rating> rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Favorite> favorite;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<WatchHistory> watchHistory;

    private boolean isDeleted;
}
