package com.yurisaito.gestore.configs;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yurisaito.gestore.entity.UserAccess;
import com.yurisaito.gestore.repository.AccessRepository;
import com.yurisaito.gestore.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class WebSecurityFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	AccessRepository accessRepository;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().contains("/auth")) {
			filterChain.doFilter(request, response);
			return;
		}

		final String token = this.recoverToken(request);

		if (token == null) {
			filterChain.doFilter(request, response);
			return;
		}

		final String username = tokenService.validateToken(token);
		Optional<UserAccess> user = accessRepository.findByUsername(username);

		if (username != null && user != null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDetails,
					null,
					userDetails.getAuthorities());
			authToken.setDetails(
					new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		var authReader = request.getHeader("Authorization");
		if (authReader == null)
			return null;
		return authReader.replace("Bearer", "");
	}
}
