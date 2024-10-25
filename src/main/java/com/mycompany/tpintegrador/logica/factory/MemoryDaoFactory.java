package com.mycompany.tpintegrador.logica.factory;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.accesodatos.impl.memory.ClienteMemory;
import com.mycompany.tpintegrador.accesodatos.impl.memory.VendedorMemory;
import com.mycompany.tpintegrador.accesodatos.impl.memory.ItemsMenuMemory;
import com.mycompany.tpintegrador.accesodatos.impl.memory.PedidoMemory;
import com.mycompany.tpintegrador.logica.factory.DaoFactory;

public class MemoryDaoFactory implements DaoFactory {

    @Override
    public ClienteDao getClienteDao() {
        return new ClienteMemory();
    }

    @Override
    public VendedorDao getVendedorDao() {
        return new VendedorMemory();
    }

    @Override
    public ItemsMenuDao getItemsMenuDao() {
        return new ItemsMenuMemory();
    }

    @Override
    public PedidoDao getPedidoDao() {
        return new PedidoMemory();
    }
}

