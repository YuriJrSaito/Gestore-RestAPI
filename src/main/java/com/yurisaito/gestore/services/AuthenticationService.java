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

    public UserAccess register(RegisterDTO access) {
        if (accessRepository.findByUsername(access.username()).isPresent()) {
            throw new UsernameIsAlredyTaken("Username " + access.username() + " is alredy taken");
        }
        final String encryptedPassword = passwordEncoder.encode(access.password());
        UserAccess newUserAccess = new UserAccess(access.username(), encryptedPassword, access.role());
        newUserAccess = accessRepository.save(newUserAccess);
        return newUserAccess;
    }
}
