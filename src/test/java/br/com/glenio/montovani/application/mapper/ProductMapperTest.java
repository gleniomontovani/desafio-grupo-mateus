package br.com.glenio.montovani.application.mapper;

import br.com.glenio.montovani.application.dto.ProductRequestDTO;
import br.com.glenio.montovani.application.dto.ProductResponseDTO;
import br.com.glenio.montovani.core.domain.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    private final ProductMapper mapper =
            Mappers.getMapper(ProductMapper.class);

    @Test
    void shouldMapRequestDtoToDomain() {
        var dto = new ProductRequestDTO(
                "Notebook",
                "Dell",
                BigDecimal.valueOf(5000),
                10
        );

        Product domain = mapper.toDomain(dto);

        assertThat(domain).isNotNull();
        assertThat(domain.getId()).isNull();
        assertThat(domain.getName()).isEqualTo("Notebook");
        assertThat(domain.getDescription()).isEqualTo("Dell");
        assertThat(domain.getPrice()).isEqualByComparingTo(BigDecimal.valueOf(5000));
        assertThat(domain.getStock()).isEqualTo(10);
    }

    @Test
    void shouldMapDomainToResponseDto() {
        var domain = Product.builder()
                .id(1L)
                .name("Notebook")
                .description("Dell")
                .price(BigDecimal.valueOf(5000))
                .stock(10)
                .build();

        ProductResponseDTO response = mapper.toResponse(domain);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.name()).isEqualTo("Notebook");
        assertThat(response.description()).isEqualTo("Dell");
        assertThat(response.price()).isEqualByComparingTo(BigDecimal.valueOf(5000));
        assertThat(response.stock()).isEqualTo(10);
    }
}
