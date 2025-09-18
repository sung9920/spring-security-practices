package config.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.session.DisableEncodeUrlFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfigEx03 {
	
	@Bean
	public FilterChainProxy springSecurityFilterChain() {
		List<SecurityFilterChain> securityFileterChains = Arrays.asList(
			new DefaultSecurityFilterChain(new AntPathRequestMatcher("/assets/**")),
			new DefaultSecurityFilterChain(new AntPathRequestMatcher("/**"), disableEncodeUrlFilter(), webAsyncManagerIntegrationFilter(), defaultLoginPageGeneratingFilter())
		);
		
		return new FilterChainProxy(securityFileterChains);
	}
	
    @Bean
    public DisableEncodeUrlFilter disableEncodeUrlFilter() {
        return new DisableEncodeUrlFilter();
    }

    @Bean
    public WebAsyncManagerIntegrationFilter webAsyncManagerIntegrationFilter() {
        return new WebAsyncManagerIntegrationFilter();
    }

    @Bean
    public DefaultLoginPageGeneratingFilter defaultLoginPageGeneratingFilter() {
        return new DefaultLoginPageGeneratingFilter();
    }
}
