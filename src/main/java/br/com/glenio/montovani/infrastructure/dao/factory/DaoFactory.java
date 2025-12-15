package br.com.glenio.montovani.infrastructure.dao.factory;

import br.com.glenio.montovani.core.ports.ProductDAO;
import br.com.glenio.montovani.infrastructure.dao.impl.ProductDaoH2;
import br.com.glenio.montovani.infrastructure.dao.impl.ProductDaoInMemory;
import org.springframework.jdbc.core.JdbcTemplate;

public class DaoFactory {
    private DaoFactory() {}

    public static ProductDAO createProductDao() {
        return new ProductDaoInMemory();
    }

    public static ProductDAO createProductDao(JdbcTemplate jdbcTemplate) {
        return new ProductDaoH2(jdbcTemplate);
    }
}
