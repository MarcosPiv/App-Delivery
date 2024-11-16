package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.ClienteService;
import com.mycompany.tpintegrador.logica.controllers.ClienteController;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TestCliente {
    private ClienteService clienteService;
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {

        clienteService = Mockito.mock(ClienteService.class);

        clienteController = new ClienteController(clienteService);
    }

    @Test
    void testMostrarListaCliente() {

        Cliente cliente1 = new Cliente(1, "12345", "email1@test.com", "Calle 1", new Coordenada(10.0, 20.0), "Cliente 1");
        Cliente cliente2 = new Cliente(2, "67890", "email2@test.com", "Calle 2", new Coordenada(30.0, 40.0), "Cliente 2");
        List<Cliente> clientesSimulados = Arrays.asList(cliente1, cliente2);

        when(clienteService.mostrarListaCliente()).thenReturn(clientesSimulados);

        List<Cliente> resultado = clienteController.mostrarListaCliente();

        assertEquals(2, resultado.size());
        assertEquals("Cliente 1", resultado.get(0).getNombre());
        assertEquals("Cliente 2", resultado.get(1).getNombre());

        verify(clienteService, times(1)).mostrarListaCliente();
    }

    @Test
    void testCrearNuevoCliente() {

        Cliente clienteSimulado = new Cliente(1, "12345", "email1@test.com", "Calle 1", new Coordenada(10.0, 20.0), "Cliente 1");
        when(clienteService.crearNuevoCliente("12345", "email1@test.com", "Calle 1", 10.0, 20.0, "Cliente 1"))
                .thenReturn(clienteSimulado);

        Cliente resultado = clienteController.crearNuevoCliente("12345", "email1@test.com", "Calle 1", 10.0, 20.0, "Cliente 1");

        assertNotNull(resultado);
        assertEquals("Cliente 1", resultado.getNombre());
        assertEquals("12345", resultado.getCuit());
        assertEquals(10.0, resultado.getCoordenada().getLat());
        assertEquals(20.0, resultado.getCoordenada().getLng());

        verify(clienteService, times(1))
                .crearNuevoCliente("12345", "email1@test.com", "Calle 1", 10.0, 20.0, "Cliente 1");
    }

    @Test
    void testModificarCliente() {

        clienteController.modificarCliente(1, "12345", "email1@test.com", "Calle 1", 10.0, 20.0, "Cliente 1");

        verify(clienteService, times(1))
                .modificarCliente(1, "12345", "email1@test.com", "Calle 1", 10.0, 20.0, "Cliente 1");
    }

    @Test
    void testEliminarCliente() {

        clienteController.eliminarCliente(1);

        verify(clienteService, times(1)).eliminarCliente(1);
    }

    @Test
    void testBuscarCliente() {

        Cliente clienteSimulado = new Cliente(1, "12345", "email1@test.com", "Calle 1", new Coordenada(10.0, 20.0), "Cliente 1");
        when(clienteService.buscarCliente(1)).thenReturn(clienteSimulado);

        Cliente resultado = clienteController.buscarCliente(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Cliente 1", resultado.getNombre());
        assertEquals(10.0, resultado.getCoordenada().getLat());
        assertEquals(20.0, resultado.getCoordenada().getLng());

        verify(clienteService, times(1)).buscarCliente(1);
    }

    @Test
    void testBuscarClientePorNombre() {

        Cliente cliente1 = new Cliente(1, "12345", "email1@test.com", "Calle 1", new Coordenada(10.0, 20.0), "Cliente 1");
        Cliente cliente2 = new Cliente(2, "67890", "email2@test.com", "Calle 2", new Coordenada(30.0, 40.0), "Cliente 1");
        List<Cliente> clientesSimulados = Arrays.asList(cliente1, cliente2);

        when(clienteService.buscarClientePorNombre("Cliente 1")).thenReturn(clientesSimulados);

        List<Cliente> resultado = clienteController.buscarClientePorNombre("Cliente 1");

        assertEquals(2, resultado.size());
        assertEquals("Cliente 1", resultado.get(0).getNombre());
        assertEquals(10.0, resultado.get(0).getCoordenada().getLat());
        assertEquals(20.0, resultado.get(0).getCoordenada().getLng());

        verify(clienteService, times(1)).buscarClientePorNombre("Cliente 1");
    }
}
