package com.yurisaito.gestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductResponseDTO;
import com.yurisaito.gestore.dtos.product.ProductRequestDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.entity.Product;
import com.yurisaito.gestore.exception.CategoryNotFoundException;
import com.yurisaito.gestore.repository.CategoryRepository;

@Service
public class ProductMapper {

    @Autowired
    CategoryRepository categoryRepository;

    public Product productCreateRequestDTOToProduct(ProductCreateRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        product.setStockQuantity(dto.stockQuantity());
        product.setSupplier(dto.supplier());
        product.setImageUrl(dto.imageUrl());
        product.setActive(dto.active());
        return product;
    }

    public Product productUpdateDtoToProduct(ProductRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Category existedCategory = categoryRepository.findById(dto.categoryId()).orElseThrow(
                () -> new CategoryNotFoundException(
                        "Category not found with ID: " + dto.categoryId()));

        Product product = new Product(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stockQuantity(),
                dto.supplier(),
                dto.imageUrl(),
                dto.active(),
                existedCategory);

        return product;
    }

    public List<ProductResponseDTO> productsToProductDtos(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }

        List<ProductResponseDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            Category category = new Category(product.getCategory().getId(), product.getCategory().getName(),
                    product.getCategory().getDescription());

            ProductResponseDTO productDTO = new ProductResponseDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getSupplier(),
                    product.getImageUrl(),
                    product.isActive(),
                    category);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public ProductResponseDTO ProductToProductResponseDTO(Product product) {
        if (product == null) {
            return null;
        }

        Category category = new Category(product.getCategory().getId(), product.getCategory().getName(),
                product.getCategory().getDescription());

        ProductResponseDTO productDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getSupplier(),
                product.getImageUrl(),
                product.isActive(),
                category);

        return productDTO;
    }
}
