package nlu.fit.movie_recommendation.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity()
@Table(name = "watch_histories")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    
}
