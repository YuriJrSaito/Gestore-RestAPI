package com.yurisaito.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.yurisaito.gestore.dtos.product.ProductCreateRequestDTO;
import com.yurisaito.gestore.dtos.product.ProductDTO;
import com.yurisaito.gestore.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper{

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product productCreateRequestDTOToProduct(ProductCreateRequestDTO dto);

    Product productDtoToProduct(ProductDTO dto);
    
    List<ProductDTO> productToProductDtos(List<Product> products);

    ProductDTO productToProductDto(Product product);
    
}
