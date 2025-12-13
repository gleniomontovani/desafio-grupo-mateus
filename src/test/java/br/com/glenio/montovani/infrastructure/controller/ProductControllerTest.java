package br.com.glenio.montovani.infrastructure.controller;

import br.com.glenio.montovani.application.dto.ProductRequestDTO;
import br.com.glenio.montovani.application.mapper.ProductMapperImpl;
import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.exception.ProductNotFoundException;
import br.com.glenio.montovani.core.usecase.ProductUseCase;
import br.com.glenio.montovani.infrastructure.handler.RestHandlerException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import({
        ProductMapperImpl.class,
        RestHandlerException.class
})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductUseCase useCase;

    private Product product() {
        return Product.builder()
                .id(1L)
                .name("Notebook")
                .description("Dell")
                .price(BigDecimal.valueOf(5000))
                .stock(10)
                .build();
    }

    private ProductRequestDTO requestDTO() {
        return new ProductRequestDTO(
                "Notebook",
                "Dell",
                BigDecimal.valueOf(5000),
                10
        );
    }

    @Test
    void shouldCreateProduct() throws Exception {
        var product = product();

        when(useCase.create(any())).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void shouldFindAllProducts() throws Exception {
        when(useCase.findAll()).thenReturn(List.of(product()));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void shouldFindProductById() throws Exception {
        when(useCase.findById(1L)).thenReturn(product());

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {
        when(useCase.findById(99L))
                .thenThrow(new ProductNotFoundException(99L));

        mockMvc.perform(get("/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message")
                        .value("Product not found with id: 99"));
    }


    @Test
    void shouldUpdateProduct() throws Exception {
        when(useCase.update(eq(1L), any())).thenReturn(product());

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isNoContent());
    }
}
