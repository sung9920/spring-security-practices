package config.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import config.WebConfig;
import jakarta.servlet.Filter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={WebConfig.class, SecurityConfigEx01.class})
@WebAppConfiguration
public class SecurityConfigEx01Test {
    private MockMvc mvc;
    private FilterChainProxy filterChainProxy;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        filterChainProxy = (FilterChainProxy) context.getBean("springSecurityFilterChain", Filter.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new DelegatingFilterProxy(filterChainProxy), "/*")
                .build();
    }

    @Test
    public void testSecurityFilterChains() {
    	List<SecurityFilterChain> securityFilterChains = filterChainProxy.getFilterChains();
    	assertEquals(2, securityFilterChains.size());
    }

    @Test
    public void testSecurityFilters() {
    	SecurityFilterChain securityFilterChain = filterChainProxy.getFilterChains().getLast();
    	List<Filter> filters = securityFilterChain.getFilters();

    	assertEquals(14, filters.size());

    	// UsernamePasswordAuthenticationFilter
    	assertEquals("UsernamePasswordAuthenticationFilter", filters.get(6).getClass().getSimpleName());

    	// DefaultResourcesFilter
    	assertEquals("DefaultResourcesFilter", filters.get(7).getClass().getSimpleName());

    	// DefaultLoginPageGeneratingFilter
    	assertEquals("DefaultLoginPageGeneratingFilter", filters.get(8).getClass().getSimpleName());

    	// DefaultLogoutPageGeneratingFilter
    	assertEquals("DefaultLogoutPageGeneratingFilter", filters.get(9).getClass().getSimpleName());
    }

    @Test
    public void testWebSecurity() throws Throwable {
    	mvc
    		.perform(get("/assets/images/logo.svg"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType("image/svg+xml"))
    		.andDo(print());
    }

    @Test
    public void testHttpSecurity() throws Throwable {
    	mvc
    		.perform(get("/ping"))
    		.andExpect(status().isOk())
    		.andExpect(content().string("pong"))
    		.andDo(print());
    }
}

