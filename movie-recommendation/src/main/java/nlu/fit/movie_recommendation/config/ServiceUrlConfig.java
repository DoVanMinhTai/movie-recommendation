package nlu.fit.movie_recommendation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "services")
public record ServiceUrlConfig(
        String chatbot, String recommendation
) {

}
