package com.yurisaito.gestore.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.seller.SellerCreateRequestDTO;
import com.yurisaito.gestore.dtos.seller.SellerResponseDTO;
import com.yurisaito.gestore.entity.Seller;
import com.yurisaito.gestore.exception.SellerNotFoundException;
import com.yurisaito.gestore.mapper.SellerMapper;
import com.yurisaito.gestore.repository.SellerRepository;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerMapper sellerMapper;

    public List<SellerResponseDTO> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        return sellerMapper.sellersToSellerResponseDTOs(sellers);
    }

    public SellerResponseDTO getSellerById(UUID sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException("Seller not found with ID: " + sellerId));

        return sellerMapper.sellerToSellerResponseDTO(seller);
    }

    public SellerResponseDTO createSeller(SellerCreateRequestDTO requestDTO) {
        //realizar atomicidade no cadastro do Seller e UserAccess
    }

    public void updateSeller() {
        //verificar se existe o seller com o id
        //converter para o mapper dto to Seller
        //repository save
        //converter para o mapper seller to response dto
        //retornar
    }

    public void deleteSeller() {
        //verificar se existe o seller com o id
        //repository delete
    }
}
