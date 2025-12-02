package nlu.fit.movie_recommendation;

import nlu.fit.movie_recommendation.config.ServiceUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "nlu.fit.movie_recommendation.repository.jpa" // ONLY look here for JPA repos
)
@EnableElasticsearchRepositories(
        basePackages = "nlu.fit.movie_recommendation.repository.elasticsearchrepository" // ONLY look here for ES repos
)
@EnableConfigurationProperties(ServiceUrlConfig.class)
public class MovieRecommendationApplication extends ElasticsearchConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommendationApplication.class, args);
	}

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withConnectTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }
}
