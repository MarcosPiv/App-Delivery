package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.ClienteService;
import com.mycompany.tpintegrador.logica.models.Cliente;
import java.util.List;

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Cliente> mostrarListaCliente() {
        return clienteService.mostrarListaCliente();
    }

    public Cliente crearNuevoCliente(String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        return clienteService.crearNuevoCliente(cuit, email, direccion, latitud, longitud, nombre);
    }

    public void modificarCliente(int id, String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        clienteService.modificarCliente(id, cuit, email, direccion, latitud, longitud, nombre);
    }

    public void eliminarCliente(int id) {
        clienteService.eliminarCliente(id);
    }

    public Cliente buscarCliente(int id) {
        return clienteService.buscarCliente(id);
    }

    public List<Cliente> buscarClientePorNombre(String nombre) {
        return clienteService.buscarClientePorNombre(nombre);
    }
}

