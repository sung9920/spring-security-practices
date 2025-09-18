package config.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
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

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={SecurityConfigEx03.class, WebConfig.class})
@WebAppConfiguration
public class SecurityConfigEx03Test {
	private static MockMvc mvc;
	private static FilterChainProxy filterChainProxy;
	
	@BeforeAll
	public static void setup(WebApplicationContext webApplicationContext) {
		filterChainProxy = webApplicationContext.getBean("springSecurityFilterChain", FilterChainProxy.class);
		mvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.addFilter(new DelegatingFilterProxy(filterChainProxy), "/*")
				.build();
	}
	
	@Test
	public void testSecurityFilterChains() {
		List<SecurityFilterChain> list = filterChainProxy.getFilterChains();
		assertEquals(2, list.size());
	}
	
	@Test
	public void testSecurityFilterChain01() {
		SecurityFilterChain securityFilterChain = filterChainProxy.getFilterChains().getFirst();
		assertEquals(0, securityFilterChain.getFilters().size());
	}

	@Test
	public void testSecurityFilterChain02() {
		SecurityFilterChain securityFilterChain = filterChainProxy.getFilterChains().getLast();
		assertEquals(3, securityFilterChain.getFilters().size());
	}
	
	@Test
	public void testHello() throws Throwable {
		mvc
			.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string("world"));
	}

	@Test
	public void testAssets() throws Throwable {
		mvc
			.perform(get("/assets/images/logo.svg"))
			.andExpect(status().isOk())
			.andExpect(content().contentType("image/svg+xml"));		
	}
	
}
