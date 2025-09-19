package config.app;

import config.WebConfig;
import jakarta.servlet.Filter;
import org.junit.jupiter.api.*;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={WebConfig.class, SecurityConfigEx04.class})
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecurityConfigEx04Test {
    private MockMvc mvc;
    private FilterChainProxy filterChainProxy;

    @BeforeEach
    public void setup(WebApplicationContext context) {
        filterChainProxy = (FilterChainProxy)context.getBean("springSecurityFilterChain", Filter.class);
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(new DelegatingFilterProxy(filterChainProxy), "/*")
                .build();
    }
    
    @Test
    @Order(1)
    public void testSecurityFilterChains() {
    	List<SecurityFilterChain> securityFilterChains = filterChainProxy.getFilterChains();
    	assertEquals(2, securityFilterChains.size());
    }

    @Test
    @Order(2)
    public void testSecurityFilters() {
    	SecurityFilterChain securityFilterChain = filterChainProxy.getFilterChains().getLast();
    	List<Filter> filters = securityFilterChain.getFilters();
    	
    	assertEquals(15, filters.size());
    	
    	// AuthorizationFilter
    	assertEquals("AuthorizationFilter", filters.get(14).getClass().getSimpleName());
    }
    
    @Test
    @Order(3)
    public void testWebSecurity() throws Throwable {
    	mvc
    		.perform(get("/assets/images/logo.svg"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType("image/svg+xml"))
    		.andDo(print());
    }

    @Test
    @Order(4)
    public void testPing() throws Throwable {
    	mvc
    		.perform(get("/ping"))
    		.andExpect(status().is3xxRedirection())
    		.andExpect(redirectedUrl("http://localhost/login"))
    		.andDo(print());
    }
    
    @Test
    @Order(5)
    public void testLogin() throws Throwable {
    	mvc
    		.perform(get("/login"))
    		.andExpect(status().isOk())
    		.andExpect(content().contentType("text/html;charset=UTF-8"))
    		.andDo(print());
    }
}
