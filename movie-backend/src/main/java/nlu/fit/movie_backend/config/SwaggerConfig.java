package nlu.fit.movie_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Movie Recommendation API", version = "1.0"), servers = {
        @Server(url = "http://localhost:8080",
                description = "Localhost Server")
})
//@SecurityScheme()
public class SwaggerConfig {
}
