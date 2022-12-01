package sg.triquesta.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.util.ArrayList;

@Configuration
public class Swagger30Config {

    @Value("${application.name}")
    private String applicationName;

    @Value("${api.version}")
    private String apiVersion;

    public Swagger30Config(MappingJackson2HttpMessageConverter converter) {
        ArrayList<MediaType> supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
        supportedMediaTypes.add(new MediaType("application", "octet-stream"));
        converter.setSupportedMediaTypes(supportedMediaTypes);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        final String apiTitle = String.format("%s API", StringUtils.capitalize(applicationName));
        return new OpenAPI()
                .info(new Info().title(apiTitle).version(apiVersion));
    }
}
