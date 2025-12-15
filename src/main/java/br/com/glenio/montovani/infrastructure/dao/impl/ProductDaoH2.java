package br.com.glenio.montovani.infrastructure.dao.impl;

import br.com.glenio.montovani.core.domain.Product;
import br.com.glenio.montovani.core.ports.ProductDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductDaoH2 implements ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) ->
            new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getBigDecimal("price"),
                    rs.getInt("stock")
            );

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO product (name, description, price, stock) VALUES (?, ?, ?, ?)",
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            );

            Long id = jdbcTemplate.queryForObject(
                    "SELECT MAX(id) FROM product", Long.class);

            return new Product(
                    id,
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    product.getStock()
            );
        }

        jdbcTemplate.update(
                "UPDATE product SET name=?, description=?, price=?, stock=? WHERE id=?",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getId()
        );

        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        var result = jdbcTemplate.query(
                "SELECT * FROM product WHERE id=?",
                ROW_MAPPER,
                id
        );
        return result.stream().findFirst();
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM product",
                ROW_MAPPER
        );
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }
}
