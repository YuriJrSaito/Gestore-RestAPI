package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.exception.ProductNotFoundException;
import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.dtos.product.ProductUpdateRequestDTO;
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
        return productMapper.mapToDTOs(products);
    }

    public ProductDTO getProductDTOById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return productMapper.mapToDTO(product);
    }

    public ProductDTO createProduct(ProductCreateRequestDTO requestDTO) {
        Product newProduct = productMapper.mapToEntity(requestDTO);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.mapToDTO(savedProduct);
    }

    public ProductDTO updateProduct(UUID productId, ProductUpdateRequestDTO requestDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        // Atualize os atributos do produto com base nos dados fornecidos em requestDTO
        existingProduct = existingProduct.withName(requestDTO.name()).withPrice(requestDTO.price());

        Product updatedProduct = productRepository.save(existingProduct);
        return productMapper.mapToDTO(updatedProduct);
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
