package jp.ac.hcs.s3a210.zipcode;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebConfig {
	
	@Bean
	public RestTemplate reatTemplate() {
		return new RestTemplate();
	}
}
