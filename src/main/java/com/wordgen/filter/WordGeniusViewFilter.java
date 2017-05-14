package com.wordgen.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class WordGeniusViewFilter implements Filter {
	
	private Logger log = Logger.getLogger((WordGeniusViewFilter.class));
	
	public void destroy() {
		log.debug("Destroying view filter...");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		// 获得用户请求的URI
		String uri = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();
		Object currUserObj = session.getAttribute("user");
		log.debug("Path of URI: " + uri);
		if(uri.indexOf("/login") > -1) {
			filterchain.doFilter(httpRequest, httpResponse);
			return;
		}
		if(currUserObj == null) {
			httpResponse.sendRedirect(contextPath + "/login");
		} else {
			 filterchain.doFilter(httpRequest, httpResponse);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		log.debug("Initiating view filter...");
	}

}
