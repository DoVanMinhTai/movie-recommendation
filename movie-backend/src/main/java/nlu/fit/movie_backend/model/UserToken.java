package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;
import nlu.fit.movie_backend.model.enumeration.ROLE;

import java.util.List;

@Entity()
@Table(name = "user_tokens")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private boolean revoked;

    @ManyToOne
    private User user;
}
