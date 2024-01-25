package com.yurisaito.gestore.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.error.ErrorResponse;
import com.yurisaito.gestore.exception.ProductNotFoundException;
import com.yurisaito.gestore.services.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProductDTOs();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getOne/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable UUID productId) {
        try {
            ProductDTO productDTO = productService.getProductDTOById(productId);
            return ResponseEntity.ok(productDTO);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
            @RequestBody @Valid ProductCreateRequestDTO productCreateRequestDTO) {
        try {
            ProductDTO createdProduct = productService.createProduct(productCreateRequestDTO);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log da exceção ou qualquer outra ação necessária
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to create product"));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestBody @Valid ProductDTO productDto) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping("delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable UUID productId) {
        try {
            productService.deleteProduct(productId);
        } catch (ProductNotFoundException ignored) {
            // Ignorado, pois não precisamos retornar um status HTTP específico ao excluir
            // um produto que não existe.
        }
    }

    // Tratamento global de exceções
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }
}
