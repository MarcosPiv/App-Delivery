package com.mycompany.tpintegrador.logica.Service;

import com.mycompany.tpintegrador.logica.models.Cliente;
import java.util.List;

public interface IClienteService {
    Cliente crearNuevoCliente(String cuit, String email, String direccion, double latitud, double longitud, String nombre);

    void modificarCliente(int id, String cuit, String email, String direccion, double latitud, double longitud, String nombre);

    void eliminarCliente(int id);

    Cliente buscarCliente(int id);

    List<Cliente> buscarClientePorNombre(String nombre);
}
