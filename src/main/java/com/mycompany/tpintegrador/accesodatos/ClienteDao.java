package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Cliente;

import java.util.List;

public interface ClienteDao {
    List<Cliente> listarClientes();
    void crearCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
    Cliente buscarCliente(int id);
    List<Cliente> buscarClientePorNombre(String nombre);
}
