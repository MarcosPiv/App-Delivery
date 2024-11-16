package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.ClienteService;
import com.mycompany.tpintegrador.logica.controllers.ClienteController;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestClienteBDD {

    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteController = new ClienteController(clienteService);
    }

    @Test
    void testClientesIdNoNulo() {

        List<Cliente> mockClientes = Arrays.asList(
                new Cliente(1, "12345678901", "cliente1@example.com", "Direccion 1", new Coordenada(10.0, 20.0), "Cliente 1"),
                new Cliente(2, "12345678902", "cliente2@example.com", "Direccion 2", new Coordenada(11.0, 21.0), "Cliente 2"),
                new Cliente(3, "12345678903", "cliente3@example.com", "Direccion 3", new Coordenada(12.0, 22.0), "Cliente 3")
        );
        when(clienteService.mostrarListaCliente()).thenReturn(mockClientes);

        List<Cliente> clientes = clienteController.mostrarListaCliente();

        for (Cliente cliente : clientes) {
            assertNotNull(cliente.getId(), "El cliente tiene un ID nulo: " + cliente);
        }

        verify(clienteService, times(1)).mostrarListaCliente();
    }

    @Test
    void testCuitNoDuplicado() {
        List<Cliente> mockClientes = Arrays.asList(
                new Cliente(1, "12345678901", "cliente1@example.com", "Direccion 1", new Coordenada(10.0, 20.0), "Cliente 1"),
                new Cliente(2, "12345678902", "cliente2@example.com", "Direccion 2", new Coordenada(11.0, 21.0), "Cliente 2"),
                new Cliente(3, "12345678903", "cliente3@example.com", "Direccion 3", new Coordenada(12.0, 22.0), "Cliente 3")
        );
        when(clienteService.mostrarListaCliente()).thenReturn(mockClientes);

        List<Cliente> clientes = clienteController.mostrarListaCliente();

        Set<String> cuitSet = new HashSet<>();
        for (Cliente cliente : clientes) {
            boolean added = cuitSet.add(cliente.getCuit());
            assertTrue(added, "El CUIT está duplicado: " + cliente.getCuit());
        }

        verify(clienteService, times(1)).mostrarListaCliente();
    }
}
