package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.entity.Category;
import com.yurisaito.gestore.entity.Product;
import com.yurisaito.gestore.exception.CategoryNotFoundException;
import com.yurisaito.gestore.exception.ProductNameDuplicateException;
import com.yurisaito.gestore.exception.ProductNotFoundException;
import com.yurisaito.gestore.mapper.ProductMapper;
import com.yurisaito.gestore.repository.CategoryRepository;
import com.yurisaito.gestore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        return productMapper.productsToProductDtos(products);
    }

    public ProductDTO getProductDTOById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return productMapper.productToProductDto(product);
    }

    public ProductDTO createProduct(ProductCreateRequestDTO requestDTO) {
        if (productRepository.findByName(requestDTO.name()) != null) {
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Product newProduct = productMapper.productCreateRequestDTOToProduct(requestDTO);

        Category category = categoryRepository.findById(requestDTO.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with ID: " + requestDTO.categoryId()));

        newProduct.setCategory(category);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.productToProductDto(savedProduct);
    }

    // verificar o produto com o mesmo nome não possui o mesmo id
    public ProductDTO updateProduct(ProductDTO requestDTO) {
        if (!productRepository.existsById(requestDTO.id())) {
            throw new ProductNotFoundException("Product not found with ID: " + requestDTO.id());
        }

        if (productRepository.findByName(requestDTO.name()) != null) {
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Category category = categoryRepository.findById(requestDTO.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with ID: " + requestDTO.categoryId()));

        Product updatedProduct = productMapper.productDtoToProduct(requestDTO);
        updatedProduct.setCategory(category);
        updatedProduct = productRepository.save(updatedProduct);

        return productMapper.productToProductDto(updatedProduct);
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
