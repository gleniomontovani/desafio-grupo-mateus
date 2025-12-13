package br.com.glenio.montovani.infrastructure.dao.factory;

import br.com.glenio.montovani.core.ports.ProductDAO;
import br.com.glenio.montovani.infrastructure.dao.impl.ProductDAOImpl;

public class DaoFactory {
    private DaoFactory() {}

    public static ProductDAO createProductDao() {
        return new ProductDAOImpl();
    }
}
