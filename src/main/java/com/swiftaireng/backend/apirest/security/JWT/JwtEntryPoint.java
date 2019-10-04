package com.swiftaireng.backend.apirest.security.JWT;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

	private static  final Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("faill method commence");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "bad credentials");
		
	}

}
