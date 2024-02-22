package com.yurisaito.gestore.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductResponseDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.entity.Product;
import com.yurisaito.gestore.exception.CategoryNotFoundException;
import com.yurisaito.gestore.exception.ProductNameDuplicateException;
import com.yurisaito.gestore.exception.ProductNotFoundException;
import com.yurisaito.gestore.mapper.ProductMapper;
import com.yurisaito.gestore.repository.CategoryRepository;
import com.yurisaito.gestore.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<ProductResponseDTO> getAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductDtos(products);
    }

    public ProductResponseDTO getProductDTOById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return productMapper.ProductToProductResponseDTO(product);
    }

    public ProductResponseDTO createProduct(ProductCreateRequestDTO requestDTO) {
        if (productRepository.findByName(requestDTO.name()) != null) {
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Product newProduct = productMapper.productCreateRequestDTOToProduct(requestDTO);

        Category category = categoryRepository.findById(requestDTO.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with ID: " + requestDTO.categoryId()));

        newProduct.setCategory(category);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.ProductToProductResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(ProductRequestDTO requestDTO) {
        if (!productRepository.existsById(requestDTO.id())) {
            throw new ProductNotFoundException("Product not found with ID: " + requestDTO.id());
        }

        if (productRepository.findByNameAndIdNot(requestDTO.name(), requestDTO.id()) != null) {
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Product updatedProduct = productMapper.productUpdateDtoToProduct(requestDTO);

        updatedProduct = productRepository.save(updatedProduct);

        return productMapper.ProductToProductResponseDTO(updatedProduct);
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
