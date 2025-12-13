package br.com.glenio.montovani.core.ports;

import br.com.glenio.montovani.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void delete(Long id);
}
