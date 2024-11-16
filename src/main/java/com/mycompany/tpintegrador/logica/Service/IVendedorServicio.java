package com.mycompany.tpintegrador.logica.Service;

import com.mycompany.tpintegrador.logica.models.Vendedor;
import java.util.List;

public interface IVendedorServicio {

    List<Vendedor> mostrarListaVendedor();

    Vendedor crearNuevoVendedor(String nombre, String direccion, double latitud, double longitud);

    void modificarVendedor(int id, String nombre, String direccion, double latitud, double longitud);

    void eliminarVendedor(int id);

    Vendedor buscarVendedor(int id) ;

    List<Vendedor> buscarVendedorPorNombre(String nombre);
}
