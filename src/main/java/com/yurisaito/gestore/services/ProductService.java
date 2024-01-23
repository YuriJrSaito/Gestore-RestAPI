package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.exception.ProductNameDuplicateException;
import com.yurisaito.exception.ProductNotFoundException;
import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.entity.Product;
import com.yurisaito.gestore.repository.ProductRepository;
import com.yurisaito.mapper.ProductMapper;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        return productMapper.productToProductDtos(products);
    }

    public ProductDTO getProductDTOById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return productMapper.productToProductDto(product);
    }

    public ProductDTO createProduct(ProductCreateRequestDTO requestDTO) {
        if(productRepository.findByName(requestDTO.name()) != null){
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Product newProduct = productMapper.productCreateRequestDTOToProduct(requestDTO);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.productToProductDto(savedProduct);
    }

    public ProductDTO updateProduct(ProductDTO requestDTO) {
        if(!productRepository.existsById(requestDTO.id())) {
            throw new ProductNotFoundException("Product not found with ID: " + requestDTO.id());
        }

        Product updatedProduct = productMapper.productDtoToProduct(requestDTO);
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
