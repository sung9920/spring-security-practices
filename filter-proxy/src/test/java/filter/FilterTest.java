package filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import config.AppConfig;
import config.WebConfig;
import jakarta.servlet.Filter;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class FilterTest {
	private static MockMvc mvc;
	
	@BeforeAll
	public static void setup(WebApplicationContext webApplicationContext) {
		Filter filter = (Filter)webApplicationContext.getBean("realFilter");
		mvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.addFilter(new DelegatingFilterProxy(filter), "/*")
				.build();
	}
	
	@Test
	public void test1() throws Exception {
		mvc
			.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(cookie().value("RealFilter", "Works"))
			.andExpect(MockMvcResultMatchers.content().string("Hello World"))
			.andDo(MockMvcResultHandlers.print());
	}
	
}
