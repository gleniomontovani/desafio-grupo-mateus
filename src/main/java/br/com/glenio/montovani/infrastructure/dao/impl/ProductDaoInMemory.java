package br.com.glenio.montovani.infrastructure.dao.impl;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.ports.ProductDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductDaoInMemory implements ProductDAO {

    private final Map<Long, Product> database = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product = Product.builder()
                    .id(sequence.getAndIncrement())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .description(product.getDescription())
                    .build();
        }
        database.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void delete(Long id) {
        database.remove(id);
    }
}

