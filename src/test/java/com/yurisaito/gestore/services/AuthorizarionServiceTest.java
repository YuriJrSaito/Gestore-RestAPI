package com.yurisaito.gestore.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yurisaito.gestore.repository.AccessRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorizarionServiceTest {
	
	@InjectMocks
	private AuthorizationService authorizationService;
	
	@Mock
	private AccessRepository accessRepository;
}
