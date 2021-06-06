package es_ontrack.backend.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@SpringBootApplication
public class SrcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrcApplication.class, args);
	}

}
