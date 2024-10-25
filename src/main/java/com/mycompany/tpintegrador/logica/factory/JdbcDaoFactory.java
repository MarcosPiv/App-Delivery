package com.mycompany.tpintegrador.logica.factory;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.accesodatos.impl.jdbc.ClienteJDBC;
import com.mycompany.tpintegrador.accesodatos.impl.jdbc.VendedorJDBC;
import com.mycompany.tpintegrador.accesodatos.impl.jdbc.ItemsMenuJDBC;
import com.mycompany.tpintegrador.accesodatos.impl.jdbc.PedidoJDBC;
import com.mycompany.tpintegrador.logica.factory.DaoFactory;

import java.sql.Connection;

public class JdbcDaoFactory implements DaoFactory {

    private Connection connection;

    public JdbcDaoFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ClienteDao getClienteDao() {
        return new ClienteJDBC(connection);
    }

    @Override
    public VendedorDao getVendedorDao() {
        return new VendedorJDBC(connection);
    }

    @Override
    public ItemsMenuDao getItemsMenuDao() {
        return new ItemsMenuJDBC(connection);
    }

    @Override
    public PedidoDao getPedidoDao() {
        return new PedidoJDBC(connection);
    }
}

