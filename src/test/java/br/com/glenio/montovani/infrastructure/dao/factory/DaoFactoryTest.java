package br.com.glenio.montovani.infrastructure.dao.factory;

import br.com.glenio.montovani.core.ports.ProductDAO;
import br.com.glenio.montovani.infrastructure.dao.impl.ProductDAOImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DaoFactoryTest {

    @Test
    void shouldCreateProductDao() {
        ProductDAO dao = DaoFactory.createProductDao();

        assertThat(dao).isNotNull();
        assertThat(dao).isInstanceOf(ProductDAOImpl.class);
    }
}
