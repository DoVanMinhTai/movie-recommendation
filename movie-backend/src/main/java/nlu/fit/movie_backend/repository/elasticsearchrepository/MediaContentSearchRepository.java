package nlu.fit.movie_backend.repository.elasticsearchrepository;

import nlu.fit.movie_backend.document.MediaContentDocument;
import nlu.fit.movie_backend.model.MediaContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaContentSearchRepository extends ElasticsearchRepository<MediaContentDocument,String> {
    @Query("{\"bool\": {\"must\": [{\"match_phrase_prefix\": {\"title\": \"?0\"}}]}}")
    List<MediaContentDocument> findAllByTitleContaining(String title);

    @Query("{\"bool\": {\"must\": [{\"match_phrase_prefix\": {\"title\": \"?0\"}}]}}")
    Page<MediaContentDocument> findAllByTitleContaining(String title, Pageable pageable);
}

