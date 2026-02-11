package nlu.fit.movie_backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import nlu.fit.movie_backend.model.User;
import nlu.fit.movie_backend.repository.jpa.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class JWTService {
    private final String SECRET = "bXktc3VwZXItbG9uZy1zZWNyZXQta2V5LWZvci1qd3QtaHMxMjM=";

    public String generateJWTToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserName())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .compact();
    }
}


