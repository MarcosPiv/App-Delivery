package com.mycompany.tpintegrador.logica.factory;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.accesodatos.PedidoDao;

public interface DaoFactory {
    ClienteDao getClienteDao();
    VendedorDao getVendedorDao();
    ItemsMenuDao getItemsMenuDao();
    PedidoDao getPedidoDao();
}


