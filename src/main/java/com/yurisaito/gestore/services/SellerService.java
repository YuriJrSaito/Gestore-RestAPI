package com.yurisaito.gestore.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yurisaito.gestore.dtos.seller.SellerCreateRequestDTO;
import com.yurisaito.gestore.dtos.seller.SellerResponseDTO;
import com.yurisaito.gestore.dtos.seller.SellerUpdateRequestDTO;
import com.yurisaito.gestore.entity.Seller;
import com.yurisaito.gestore.entity.UserAccess;
import com.yurisaito.gestore.exception.SellerCpfDuplicateException;
import com.yurisaito.gestore.exception.SellerNotFoundException;
import com.yurisaito.gestore.mapper.SellerMapper;
import com.yurisaito.gestore.repository.SellerRepository;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private AuthenticationService authenticationService;

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

    @Transactional
    public SellerResponseDTO createSeller(SellerCreateRequestDTO requestDTO) {
        requestDTO.validate();

        UserAccess access = authenticationService.register(requestDTO.access());

        if (sellerRepository.findByCpf(requestDTO.cpf()) != null) {
            throw new SellerCpfDuplicateException("Seller with CPF " + requestDTO.cpf() + " exist");
        }

        Seller newSeller = sellerMapper.SellerCreateRequestToSeller(requestDTO);

        newSeller.setUserAccess(access);
        sellerRepository.save(newSeller);
        return sellerMapper.sellerToSellerResponseDTO(newSeller);
    }

    public SellerResponseDTO updateSeller(SellerUpdateRequestDTO requestDTO) {
        if (sellerRepository.findByCpfAndIdNot(requestDTO.cpf(), requestDTO.id()) != null) {
            throw new SellerCpfDuplicateException("Seller with CPF " + requestDTO.cpf() + " exist");
        }

        Seller newSeller = sellerRepository.save(sellerMapper.SellerUpdateRequestDTOToSeller(requestDTO));
        return sellerMapper.sellerToSellerResponseDTO(newSeller);
    }

    public void deleteSeller(UUID sellerId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new SellerNotFoundException("Seller not found with id: " + sellerId);
        }
        sellerRepository.deleteById(sellerId);
    }
}
