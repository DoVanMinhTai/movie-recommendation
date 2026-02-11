package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Table(name = "series")
@Entity()
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "SERIES")
public class Series extends MediaContent {

    private String status;

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    @OrderBy("seasonNumber ASC")
    private List<Season> seasons;

}
