package com.yurisaito.gestore.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yurisaito.gestore.repository.AccessRepository;
import com.yurisaito.gestore.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class WebSecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	TokenService tokenService;

	@Autowired
	AccessRepository accessRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
			throws ServletException, IOException {

		var token = this.recoverToken(request);
		
		if(token != null){
			var login = tokenService.validateToken(token);
			UserDetails user = accessRepository.findByUsername(login);

			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request){
		var authReader = request.getHeader("Authorization");
		if(authReader == null)
			return null;
		return authReader.replace("Bearer", "");
	}
}
