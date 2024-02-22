package com.yurisaito.gestore.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yurisaito.gestore.dtos.seller.SellerResponseDTO;
import com.yurisaito.gestore.entity.Seller;

@Service
public class SellerMapper {
    public List<SellerResponseDTO> sellersToSellerResponseDTOs(List<Seller> sellers){
        if(sellers == null || sellers.isEmpty()){
            return null;
        }

        List<SellerResponseDTO> sellersDto = new ArrayList<>();

        for(Seller seller : sellers){
            
            SellerResponseDTO sellerDto = new SellerResponseDTO(
                seller.getID(),
                seller.getName(),
                seller.getEmail(),
                seller.getPhone(),
                seller.getRegistrationDate());

            sellersDto.add(sellerDto);
        }

        return sellersDto;
    }

    public SellerResponseDTO sellerToSellerResponseDTO(Seller seller){
        if(seller == null){
            return null;
        }

        SellerResponseDTO sellerDto = new SellerResponseDTO(
            seller.getID(),
            seller.getName(),
            seller.getEmail(),
            seller.getPhone(),
            seller.getRegistrationDate()
        );

        return sellerDto;
    }
}
