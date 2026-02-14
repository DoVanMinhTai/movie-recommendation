package nlu.fit.movie_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @JsonIgnore
    private List<MediaContent> mediaContents;

    @ManyToMany(mappedBy = "preferredGenres",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> users;
}
