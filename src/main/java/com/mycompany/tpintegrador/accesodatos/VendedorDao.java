package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  VendedorDao extends CrudRepository<Vendedor, Integer> {
    List<Vendedor> findVendedorsByNombre(String nombre);
}

