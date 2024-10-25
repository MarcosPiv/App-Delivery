package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;

import java.util.List;

public class ClienteController {

    private ClienteDao clienteDao;

    public ClienteController(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Cliente> mostrarListaCliente() {
        return clienteDao.listarClientes();
    }

    public Cliente crearNuevoCliente(String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCuit(cuit);
        nuevoCliente.setEmail(email);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setCoordenada(new Coordenada(latitud, longitud));
        nuevoCliente.setNombre(nombre);

        clienteDao.crearCliente(nuevoCliente);
        return nuevoCliente;
    }

    public void modificarCliente(int id, String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        Cliente clienteExistente = clienteDao.buscarCliente(id);
        if (clienteExistente != null) {
            clienteExistente.setCuit(cuit);
            clienteExistente.setEmail(email);
            clienteExistente.setDireccion(direccion);
            clienteExistente.setCoordenada(new Coordenada(latitud, longitud));
            clienteExistente.setNombre(nombre);
            clienteDao.actualizarCliente(clienteExistente);
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public void eliminarCliente(int id) {
        Cliente cliente = clienteDao.buscarCliente(id);
        if (cliente != null) {
            clienteDao.eliminarCliente(id);
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public Cliente buscarCliente(int id) {
        return clienteDao.buscarCliente(id);
    }
}

