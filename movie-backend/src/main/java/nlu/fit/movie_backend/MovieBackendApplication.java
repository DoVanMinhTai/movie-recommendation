package nlu.fit.movie_backend;

import nlu.fit.movie_backend.config.ServiceUrlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "nlu.fit.movie_backend.repository.jpa"
)
@EnableElasticsearchRepositories(
        basePackages = "nlu.fit.movie_backend.repository.elasticsearchrepository"
)
@EnableConfigurationProperties(ServiceUrlConfig.class)
public class MovieBackendApplication extends ElasticsearchConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(MovieBackendApplication.class, args);
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
