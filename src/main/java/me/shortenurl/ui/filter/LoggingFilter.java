package me.shortenurl.ui.filter;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //스프링부트에서 빈으로 등록하면 자동 필터 등록 가능, 스프링 부트는 Filter 타입의 빈을 감지하면 FilterRegistrationBean을 통해 자동 등록
public class LoggingFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException, ServletException {

		if (servletRequest instanceof HttpServletRequest httpServletRequest) {

			CachedBodyHttpServeltRequest wrappedRequest =
				new CachedBodyHttpServeltRequest(httpServletRequest);

			String requestURI = wrappedRequest.getRequestURI();
			String method = wrappedRequest.getMethod();
			String body = wrappedRequest.getReader().lines().reduce("", String::concat);

			log.trace("method = {}, uri = {}, body = {}", method, requestURI, body);

			filterChain.doFilter(wrappedRequest, servletResponse);
		}else{
			filterChain.doFilter(servletRequest, servletResponse);
		}

	}

}
