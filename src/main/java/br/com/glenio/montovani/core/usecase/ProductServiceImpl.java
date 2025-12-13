package br.com.glenio.montovani.core.usecase;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.exception.ProductNotFoundException;
import br.com.glenio.montovani.core.ports.ProductDAO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductUseCase {

    private final ProductDAO repository;

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        var existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.update(product);
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }
}

