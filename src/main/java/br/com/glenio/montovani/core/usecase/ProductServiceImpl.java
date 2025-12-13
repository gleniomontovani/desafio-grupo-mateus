package br.com.glenio.montovani.core.usecase;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.exception.ProductNotFoundException;
import br.com.glenio.montovani.core.ports.ProductDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductUseCase {

    private final ProductDAO repository;

    @Override
    public Product create(Product product) {
        log.info("Creating product with name={}", product.getName());
        return repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        log.info("Updating product id={}", id);
        var existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.update(product);
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting product id={}", id);
        repository.delete(id);
    }

    @Override
    public Product findById(Long id) {
        log.debug("Finding product by id={}", id);
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findAll() {
        log.debug("Finding all products");
        return repository.findAll();
    }
}

