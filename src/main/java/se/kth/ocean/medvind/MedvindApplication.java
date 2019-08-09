package se.kth.ocean.medvind;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@EnableOAuth2Client
public class MedvindApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedvindApplication.class, args);
	}

	/**
	 * Create oauth rest template (spring boot magic with configuration from application.properties)
	 *
	 * @param oauth2ClientContext
	 * @param details
	 * @return
	 */
	@Bean
	public OAuth2RestTemplate stravaRestTemplate(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
		OAuth2RestTemplate result = new OAuth2RestTemplate(details, oauth2ClientContext);
		return result;
	}

}
