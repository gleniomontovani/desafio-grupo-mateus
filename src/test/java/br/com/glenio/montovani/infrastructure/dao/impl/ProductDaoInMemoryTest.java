package br.com.glenio.montovani.infrastructure.dao.impl;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.ports.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoInMemoryTest {

    private ProductDAO dao;

    @BeforeEach
    void setUp() {
        dao = new ProductDaoInMemory();
    }

    private Product productWithoutId() {
        return Product.builder()
                .name("Notebook")
                .description("Dell")
                .price(BigDecimal.valueOf(5000))
                .stock(10)
                .build();
    }

    // --------------------------------------//
    // ---------------- SAVE ----------------//
    // --------------------------------------//

    @Test
    void shouldSaveProductAndGenerateId() {
        var product = productWithoutId();

        var saved = dao.save(product);

        assertNotNull(saved.getId());
        assertEquals("Notebook", saved.getName());
    }

    @Test
    void shouldUpdateExistingProduct() {
        var product = dao.save(productWithoutId());

        var updated = Product.builder()
                .id(product.getId())
                .name("Notebook Gamer")
                .description("Dell G15")
                .price(BigDecimal.valueOf(7000))
                .stock(5)
                .build();

        var result = dao.save(updated);

        assertEquals("Notebook Gamer", result.getName());
        assertEquals(5, result.getStock());
    }

    // --------------------------------------//
    // --------------FIND BY ID--------------//
    // --------------------------------------//

    @Test
    void shouldFindProductById() {
        var saved = dao.save(productWithoutId());

        Optional<Product> result = dao.findById(saved.getId());

        assertTrue(result.isPresent());
        assertEquals(saved.getId(), result.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenProductNotFound() {
        Optional<Product> result = dao.findById(999L);

        assertTrue(result.isEmpty());
    }

    // --------------------------------------//
    // ---------------FIND ALL---------------//
    // --------------------------------------//

    @Test
    void shouldFindAllProducts() {
        dao.save(productWithoutId());
        dao.save(productWithoutId());

        var products = dao.findAll();

        assertEquals(2, products.size());
    }

    // --------------------------------------//
    // ----------------DELETE----------------//
    // --------------------------------------//

    @Test
    void shouldDeleteProduct() {
        var saved = dao.save(productWithoutId());

        dao.delete(saved.getId());

        assertTrue(dao.findById(saved.getId()).isEmpty());
    }
}
