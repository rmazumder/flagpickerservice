/*package com.example.hcl.flagpicker.config;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.trace.http.HttpExchangeTracer;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.web.trace.servlet.HttpTraceFilter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@PropertySource("classpath:application.properties")
@Component
public class RequestTraceFilter extends HttpTraceFilter {

	@Value("${httptrace.inlude.endpoints:ALL}")
	String includeProperty;
	
	
	private  String[] includeEndpoints ;

	public RequestTraceFilter(HttpTraceRepository repository, HttpExchangeTracer tracer) {
		super(repository, tracer);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(includeEndpoints == null) {
			includeEndpoints = includeProperty.split(",");
			System.out.println("httptrace include endpoints are");
			Arrays.stream(includeEndpoints).forEach(System.out::println);
		}
		
		ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

		filterChain.doFilter(requestWrapper, responseWrapper);
		responseWrapper.copyBodyToResponse();

		if (!includeProperty.equalsIgnoreCase("NONE") && (includeProperty.equalsIgnoreCase("ALL") || shouldTrace(requestWrapper))) {
			super.doFilterInternal(requestWrapper, responseWrapper, filterChain);
		}
		return;

	}

	boolean shouldTrace(final HttpServletRequest request) throws ServletException {
		return Arrays.stream(includeEndpoints).anyMatch(e -> new AntPathMatcher().match(e, request.getServletPath()));
	}


}*/