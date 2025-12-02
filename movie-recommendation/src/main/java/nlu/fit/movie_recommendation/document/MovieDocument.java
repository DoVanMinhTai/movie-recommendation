package nlu.fit.movie_recommendation.document;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "movies")
public class MovieDocument {

    @Id
    private String id;

    private String title;
    private String genre;

}
