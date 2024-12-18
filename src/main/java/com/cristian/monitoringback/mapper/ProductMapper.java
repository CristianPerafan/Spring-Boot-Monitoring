package com.cristian.monitoringback.mapper;

import com.cristian.monitoringback.dto.ProductDTO;
import com.cristian.monitoringback.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);
    ProductDTO toDTO(Product product);
}
