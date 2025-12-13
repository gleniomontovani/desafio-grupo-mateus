package br.com.glenio.montovani.application.mapper;

import br.com.glenio.montovani.application.dto.ProductRequestDTO;
import br.com.glenio.montovani.application.dto.ProductResponseDTO;
import br.com.glenio.montovani.core.domain.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductRequestDTO dto);
    ProductResponseDTO toResponse(Product domain);
}
