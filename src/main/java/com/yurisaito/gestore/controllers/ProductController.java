package com.yurisaito.gestore.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductResponseDTO;
import com.yurisaito.gestore.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProductDTOs();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getOne/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable UUID productId) {
        ProductResponseDTO productDTO = productService.getProductDTOById(productId);
        return ResponseEntity.ok(productDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreateRequestDTO productCreateRequestDTO) {
        ProductResponseDTO createdProduct = productService.createProduct(productCreateRequestDTO);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequestDTO productDto) {
        ProductResponseDTO updatedProduct = productService.updateProduct(productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID productId) {
        productService.deleteProduct(productId);
    }
}
