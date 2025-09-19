package config.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import filter.SecurityFilterEx01;
import filter.SecurityFilterEx02;
import filter.SecurityFilterEx03;
import filter.SecurityFilterEx04;

@Configuration
public class SecurityConfigEx02 {
	
	@Bean
	public FilterChainProxy springSecurityFilterChain() {		
		List<SecurityFilterChain> securityFileterChains = Arrays.asList(
			new DefaultSecurityFilterChain(new AntPathRequestMatcher("/hello/**"), securityFilterEx01(), securityFilterEx02()),
			new DefaultSecurityFilterChain(new AntPathRequestMatcher("/ping/**"), securityFilterEx03(), securityFilterEx04())
		);
		
		return new FilterChainProxy(securityFileterChains);
	}
	
    @Bean
    public SecurityFilterEx01 securityFilterEx01() {
        return new SecurityFilterEx01();
    }

    @Bean
    public SecurityFilterEx02 securityFilterEx02() {
        return new SecurityFilterEx02();
    }

    @Bean
    public SecurityFilterEx03 securityFilterEx03() {
        return new SecurityFilterEx03();
    }

    @Bean
    public SecurityFilterEx04 securityFilterEx04() {
        return new SecurityFilterEx04();
    }
}
