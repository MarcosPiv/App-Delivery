package com.mycompany.tpintegrador.accesodatos.impl.memory;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.logica.models.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteMemory implements ClienteDao {

    private List<Cliente> clientes = new ArrayList<>();

    @Override
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    @Override
    public void crearCliente(Cliente cliente) {
        cliente.setId(clientes.size() + 1); // Asignar un ID automáticamente
        clientes.add(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId() == cliente.getId()) {
                clientes.set(i, cliente);
                break;
            }
        }
    }

    @Override
    public void eliminarCliente(int id) {
        clientes.removeIf(c -> c.getId() == id);
    }

    @Override
    public Cliente buscarCliente(int id) {
        return clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cliente> buscarClientePorNombre(String nombre) {
        return clientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
    }
}
