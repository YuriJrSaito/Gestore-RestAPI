package com.yurisaito.gestore.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yurisaito.gestore.dtos.seller.SellerCreateRequestDTO;
import com.yurisaito.gestore.dtos.seller.SellerResponseDTO;
import com.yurisaito.gestore.dtos.seller.SellerUpdateRequestDTO;
import com.yurisaito.gestore.services.SellerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/v1/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/getAll")
    public ResponseEntity<List<SellerResponseDTO>> getAllSellers() {
        List<SellerResponseDTO> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("/getOne/{sellerId}")
    public ResponseEntity<?> getSellerById(@PathVariable UUID sellerId) {
        SellerResponseDTO seller = sellerService.getSellerById(sellerId);
        return ResponseEntity.ok(seller);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSeller(@RequestBody @Valid SellerCreateRequestDTO dto) {
        SellerResponseDTO createdSeller = sellerService.createSeller(dto);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSeller(@RequestBody @Valid SellerUpdateRequestDTO dto) {
        SellerResponseDTO updatedSeller = sellerService.updateSeller(dto);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/delete/{sellerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeller(@PathVariable UUID sellerId) {
        sellerService.deleteSeller(sellerId);
    }
}
