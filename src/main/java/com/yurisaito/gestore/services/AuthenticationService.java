package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.auth.AuthenticationDTO;
import com.yurisaito.gestore.dtos.auth.LoginResponseDTO;
import com.yurisaito.gestore.dtos.auth.RegisterDTO;
import com.yurisaito.gestore.entity.UserAccess;
import com.yurisaito.gestore.exception.UsernameIsAlredyTaken;
import com.yurisaito.gestore.repository.AccessRepository;

@Service
public class AuthenticationService {
    @Autowired
    AccessRepository accessRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    TokenService tokenService;

    @Autowired
    AuthenticationManager authenticationManager;

    public LoginResponseDTO authenticate(AuthenticationDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));

        UserAccess user = accessRepository.findByUsername(dto.username()).orElseThrow();
        // isLogged change here
        final String token = tokenService.generateToken(user);

        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO register(RegisterDTO dto) {
        if (accessRepository.findByUsername(dto.username()).isPresent()) {
            throw new UsernameIsAlredyTaken("Username " + dto.username() + " is alredy taken");
        }
        final String encryptedPassword = passwordEncoder.encode(dto.password());
        UserAccess newUserAccess = new UserAccess(dto.username(), encryptedPassword, dto.role());
        accessRepository.save(newUserAccess);
        final String token = tokenService.generateToken(newUserAccess);
        return new LoginResponseDTO(token);
    }
}
