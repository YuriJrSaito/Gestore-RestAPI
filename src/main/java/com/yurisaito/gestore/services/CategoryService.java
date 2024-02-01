package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.CategoryDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(CategoryDTO dto){

        Category newCategory = new Category();
        newCategory.setName(dto.name());
        newCategory.setDescription(dto.description());

        return categoryRepository.save(newCategory);
    }
}
