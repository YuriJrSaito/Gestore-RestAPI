package com.yurisaito.gestore.dtos;

import com.yurisaito.gestore.entity.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {

}
