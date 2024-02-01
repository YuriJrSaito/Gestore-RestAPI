package com.yurisaito.gestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.entity.Product;

@Service
public class ProductMapper {

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
        return product;
    }

    public Product productDtoToProduct(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        Product product = new Product(
                dto.id(),
                dto.name(),
                dto.description(),
                dto.price(),
                dto.stockQuantity(),
                dto.supplier(),
                dto.imageUrl(),
                dto.active(),
                null);

        return product;
    }

    public List<ProductDTO> productsToProductDtos(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }

        List<ProductDTO> productDTOs = new ArrayList<>();

        for (Product product : products) {
            Category category = new Category(product.getCategory().getId(), product.getCategory().getName(),
                    product.getCategory().getDescription());

            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getSupplier(),
                    product.getImageUrl(),
                    product.isActive(),
                    null);
            productDTOs.add(productDTO);
        }

        return productDTOs;
    }

    public ProductDTO productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        Category category = new Category(product.getCategory().getId(), product.getCategory().getName(),
                product.getCategory().getDescription());

        ProductDTO productDTO = new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getSupplier(),
                product.getImageUrl(),
                product.isActive(),
                null);

        return productDTO;
    }
}
