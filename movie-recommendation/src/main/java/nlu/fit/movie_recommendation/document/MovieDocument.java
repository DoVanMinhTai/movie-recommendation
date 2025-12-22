package nlu.fit.movie_recommendation.document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDocument {

    @Id
    private String id;

    @MultiField(
            mainField = @Field(type = FieldType.Text),
            otherFields = { @InnerField(suffix = "keyword", type = FieldType.Keyword) }
    )
    private String title;

    @Field(type = FieldType.Keyword)
    private String genre;

}
