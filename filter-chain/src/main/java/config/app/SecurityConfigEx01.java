package config.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

import filter.SecurityFilterEx01;
import filter.SecurityFilterEx02;
import filter.SecurityFilterEx03;
import filter.SecurityFilterEx04;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfigEx01 {
	@Bean
	public FilterChainProxy springSecurityFilterChain() {
		List<SecurityFilterChain> securityFileterChains = Arrays.asList(
				new SecurityFilterChain() {
					@Override
					public boolean matches(HttpServletRequest request) {
						String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
						return new AntPathMatcher().match("/hello/**", uri);
					}
					@Override
					public List<Filter> getFilters() {
						return Arrays.asList(securityFilterEx01(), securityFilterEx02());
					}
				},
				new SecurityFilterChain() {
					@Override
					public boolean matches(HttpServletRequest request) {
						String uri = request.getRequestURI().replaceAll(request.getContextPath(), "");
						return new AntPathMatcher().match("/ping/**", uri);
					}
					@Override
					public List<Filter> getFilters() {
						return Arrays.asList(securityFilterEx03(), securityFilterEx04());
					}
				}
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
