package com.yurisaito.gestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yurisaito.gestore.dtos.auth.AuthenticationDTO;
import com.yurisaito.gestore.dtos.auth.LoginResponseDTO;
import com.yurisaito.gestore.dtos.auth.RegisterDTO;
import com.yurisaito.gestore.entity.UserAccess;
import com.yurisaito.gestore.error.ErrorResponse;
import com.yurisaito.gestore.exception.UsernameIsAlredyTaken;
import com.yurisaito.gestore.services.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticatorController {

	@Autowired
	AuthenticationService authenticationService;

	@PostMapping("/authenticate")
	public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authDto) {
		try {
			LoginResponseDTO response = authenticationService.authenticate(authDto);
			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("Failed to authenticate this user"));
		}
	}

	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDto) {
		try {
			UserAccess response = authenticationService.register(registerDto);
			return ResponseEntity.ok(response);
		} 
		catch(UsernameIsAlredyTaken e){
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse("User cannot be registered"));
		}
	}
}
