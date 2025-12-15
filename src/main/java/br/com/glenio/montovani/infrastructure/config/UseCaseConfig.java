package br.com.glenio.montovani.infrastructure.config;

import br.com.glenio.montovani.core.usecase.ProductUseCase;
import br.com.glenio.montovani.core.usecase.ProductServiceImpl;
import br.com.glenio.montovani.core.ports.ProductDAO;
import br.com.glenio.montovani.infrastructure.dao.factory.DaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UseCaseConfig {

    @Bean
    @Profile("test")
    public ProductDAO productDAOInMemory() {
        return DaoFactory.createProductDao();
    }

    @Bean
    @Profile("h2")
    public ProductDAO productDAO(JdbcTemplate jdbcTemplate) {
        return DaoFactory.createProductDao(jdbcTemplate);
    }

    @Bean
    public ProductUseCase productUseCase(ProductDAO productDAO) {
        return new ProductServiceImpl(productDAO);
    }
}

