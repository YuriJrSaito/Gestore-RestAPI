package com.yurisaito.gestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yurisaito.gestore.dtos.AuthenticationDTO;
import com.yurisaito.gestore.dtos.LoginResponseDTO;
import com.yurisaito.gestore.dtos.RegisterDTO;
import com.yurisaito.gestore.entity.UserAccess;
import com.yurisaito.gestore.repository.AccessRepository;
import com.yurisaito.gestore.services.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticatorController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private AccessRepository accessRepository;

	@Autowired
	private TokenService tokenService;
	
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO data) {

    	UsernamePasswordAuthenticationToken usernamePassword = 
    			new UsernamePasswordAuthenticationToken(data.username(), data.password());
    	Authentication auth = this.authenticationManager.authenticate(usernamePassword);
    	
		var token = tokenService.generateToken((UserAccess)auth.getPrincipal());
    	return ResponseEntity.ok(new LoginResponseDTO(token));
    }
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDto) {
		
		if(this.accessRepository.findByUsername(registerDto.username()) != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
		UserAccess newUserAccess = new UserAccess(registerDto.username(), encryptedPassword, registerDto.role());
	
		this.accessRepository.save(newUserAccess);
		return ResponseEntity.ok().build();
	}
}
