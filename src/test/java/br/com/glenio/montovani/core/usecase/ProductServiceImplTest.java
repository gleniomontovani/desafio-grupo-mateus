package br.com.glenio.montovani.core.usecase;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.ports.ProductDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductDAO productDAO;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product() {
        return Product.builder()
                .id(1L)
                .name("Notebook")
                .description("Dell")
                .price(BigDecimal.valueOf(5000))
                .stock(10)
                .build();
    }

    private Product updatedProduct() {
        return Product.builder()
                .name("Notebook Gamer")
                .description("Dell G15")
                .price(BigDecimal.valueOf(7000))
                .stock(5)
                .build();
    }

    // ----------------------------------------//
    // ---------------- CREATE ----------------//
    // ----------------------------------------//
    @Test
    void shouldCreateProduct() {
        var product = product();

        when(productDAO.save(product)).thenReturn(product);

        var result = service.create(product);

        assertNotNull(result);
        assertEquals("Notebook", result.getName());
        verify(productDAO).save(product);
    }

    // ---------------- FIND ALL ----------------

    @Test
    void shouldFindAllProducts() {
        when(productDAO.findAll()).thenReturn(List.of(product()));

        var result = service.findAll();

        assertEquals(1, result.size());
        verify(productDAO).findAll();
    }

    // ----------------------------------------//
    // --------------FIND BY ID----------------//
    // ----------------------------------------//

    @Test
    void shouldFindProductById() {
        when(productDAO.findById(1L)).thenReturn(Optional.of(product()));

        var result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(productDAO).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFoundById() {
        when(productDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.findById(1L));

        verify(productDAO).findById(1L);
    }

    // ----------------------------------------//
    // ---------------- UPDATE ----------------//
    // ----------------------------------------//

    @Test
    void shouldUpdateProduct() {
        var existing = product();
        var updateData = updatedProduct();

        when(productDAO.findById(1L)).thenReturn(Optional.of(existing));
        when(productDAO.save(any(Product.class))).thenReturn(existing);

        var result = service.update(1L, updateData);

        assertEquals("Notebook Gamer", result.getName());
        assertEquals("Dell G15", result.getDescription());
        assertEquals(BigDecimal.valueOf(7000), result.getPrice());
        assertEquals(5, result.getStock());

        verify(productDAO).findById(1L);
        verify(productDAO).save(existing);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingProduct() {
        when(productDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> service.update(1L, updatedProduct()));

        verify(productDAO).findById(1L);
        verify(productDAO, never()).save(any());
    }

    // ----------------------------------------//
    // ---------------- DELETE ----------------//
    // ----------------------------------------//

    @Test
    void shouldDeleteProduct() {
        doNothing().when(productDAO).delete(1L);

        service.delete(1L);

        verify(productDAO).delete(1L);
    }
}
