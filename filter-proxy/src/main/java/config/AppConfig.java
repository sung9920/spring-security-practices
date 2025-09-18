package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import filter.RealFilter;
import jakarta.servlet.Filter;

@Configuration
public class AppConfig {
	
	@Bean
	public Filter realFilter() {
		return new RealFilter();
	}
}
