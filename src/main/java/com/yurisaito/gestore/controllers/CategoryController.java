package com.yurisaito.gestore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yurisaito.gestore.dtos.CategoryDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.error.ErrorResponse;
import com.yurisaito.gestore.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDTO dto){
        try {
            Category category = categoryService.create(dto);
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Failed to create the category"));
        }
    }
}
