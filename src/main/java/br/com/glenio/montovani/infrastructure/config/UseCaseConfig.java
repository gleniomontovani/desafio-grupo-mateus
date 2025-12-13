package br.com.glenio.montovani.infrastructure.config;

import br.com.glenio.montovani.core.usecase.ProductUseCase;
import br.com.glenio.montovani.core.usecase.ProductServiceImpl;
import br.com.glenio.montovani.core.ports.ProductDAO;
import br.com.glenio.montovani.infrastructure.dao.factory.DaoFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ProductDAO productDAO() {
        return DaoFactory.createProductDao();
    }

    @Bean
    public ProductUseCase productUseCase(ProductDAO productDAO) {
        return new ProductServiceImpl(productDAO);
    }
}

