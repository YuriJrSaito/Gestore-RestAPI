package com.yurisaito.gestore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.entity.Product;
import com.yurisaito.gestore.exception.ProductNameDuplicateException;
import com.yurisaito.gestore.exception.ProductNotFoundException;
import com.yurisaito.gestore.mapper.ProductMapper;
import com.yurisaito.gestore.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProductDTOs() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.INSTANCE.productToProductDtos(products);
    }

    public ProductDTO getProductDTOById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));
        return ProductMapper.INSTANCE.productToProductDto(product);
    }

    public ProductDTO createProduct(ProductCreateRequestDTO requestDTO) {
        if(productRepository.findByName(requestDTO.name()) != null){
            throw new ProductNameDuplicateException("Product with the same name already exists");
        }

        Product newProduct = ProductMapper.INSTANCE.productCreateRequestDTOToProduct(requestDTO);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.INSTANCE.productToProductDto(savedProduct);
    }

    public ProductDTO updateProduct(ProductDTO requestDTO) {
        if(!productRepository.existsById(requestDTO.id())) {
            throw new ProductNotFoundException("Product not found with ID: " + requestDTO.id());
        }

        Product updatedProduct = ProductMapper.INSTANCE.productDtoToProduct(requestDTO);
        updatedProduct = productRepository.save(updatedProduct);

        return ProductMapper.INSTANCE.productToProductDto(updatedProduct);
    }

    public void deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
