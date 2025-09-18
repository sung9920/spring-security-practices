package filter;

import java.io.IOException;

import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RealFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Cookie cookie = new Cookie("RealFilter", "Works");
		cookie.setPath(((HttpServletRequest)request).getContextPath());
		cookie.setMaxAge(60);
		((HttpServletResponse)response).addCookie(cookie);

		chain.doFilter(request, response);
	}
}
