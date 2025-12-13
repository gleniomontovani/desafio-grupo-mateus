package br.com.glenio.montovani.core.usecase;

import br.com.glenio.montovani.core.domain.Product;

import java.util.List;

public interface ProductUseCase {

    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
    Product findById(Long id);
    List<Product> findAll();
}
