package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Vendedor;
import java.util.List;

public interface  VendedorDao {
    List<Vendedor> listarVendedores();
    void crearVendedor(Vendedor vendedor);
    void actualizarVendedor(Vendedor vendedor);
    void eliminarVendedor(int id);
    Vendedor buscarVendedorPorId(int id);
    List<Vendedor> buscarVendedorPorNombre(String nombre);
}

