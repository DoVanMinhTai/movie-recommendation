package nlu.fit.movie_backend.model;

import jakarta.persistence.*;
import lombok.*;
import nlu.fit.movie_backend.model.enumeration.ROLE;

import java.util.List;

@Entity()
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'USER'")
    private ROLE role = ROLE.USER;

    private String fullName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WatchHistory> watchHistory;

    private boolean isDeleted;
}
