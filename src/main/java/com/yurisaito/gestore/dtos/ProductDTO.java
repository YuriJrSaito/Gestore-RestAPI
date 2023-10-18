package com.yurisaito.gestore.dtos;

import com.yurisaito.gestore.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductDTO (@NotNull Double price, String code, @NotBlank String title, String descripion, 
		Integer quantitySold, @NotNull Category category){
	
}
