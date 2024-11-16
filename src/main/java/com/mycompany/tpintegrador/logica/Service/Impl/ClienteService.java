package com.mycompany.tpintegrador.logica.Service.Impl;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.tpintegrador.logica.Service.IClienteService;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public List<Cliente> mostrarListaCliente() {
        return clienteDao.findAll();
    }

    public Cliente crearNuevoCliente(String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setCuit(cuit);
        nuevoCliente.setEmail(email);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setCoordenada(new Coordenada(latitud, longitud));
        nuevoCliente.setNombre(nombre);

        clienteDao.save(nuevoCliente);
        return nuevoCliente;
    }

    public void modificarCliente(int id, String cuit, String email, String direccion, double latitud, double longitud, String nombre) {
        Optional<Cliente> clienteExistente = clienteDao.findById(id);
        if (clienteExistente.isPresent()) {
            clienteExistente.get().setCuit(cuit);
            clienteExistente.get().setEmail(email);
            clienteExistente.get().setDireccion(direccion);
            clienteExistente.get().setCoordenada(new Coordenada(latitud, longitud));
            clienteExistente.get().setNombre(nombre);
            clienteDao.save(clienteExistente.get());
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }

    public void eliminarCliente(int id) {
        Optional<Cliente> cliente = clienteDao.findById(id);
        if (cliente.isPresent()) {
            clienteDao.deleteById(id);
        } else {
            System.out.println("Cliente con ID " + id + " no encontrado.");
        }
    }


    public Cliente buscarCliente(int id) {
        Optional<Cliente> cliente = clienteDao.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrada."));
    }

    public List<Cliente> buscarClientePorNombre(String nombre) {
        return clienteDao.findClienteByNombre(nombre);
    }

}
