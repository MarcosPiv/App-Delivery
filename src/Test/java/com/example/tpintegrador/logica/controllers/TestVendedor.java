package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.VendedorService;
import com.mycompany.tpintegrador.logica.controllers.VendedorController;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestVendedor {
    private VendedorController vendedorController;

    @Mock
    private VendedorService vendedorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vendedorController = new VendedorController(vendedorService);
    }

    @Test
    void testMostrarListaVendedor() {
        List<Vendedor> mockVendedores = new ArrayList<>();
        when(vendedorService.mostrarListaVendedor()).thenReturn(mockVendedores);

        List<Vendedor> result = vendedorController.mostrarListaVendedor();

        assertNotNull(result);
        assertEquals(mockVendedores, result);
        verify(vendedorService, times(1)).mostrarListaVendedor();
    }

    @Test
    void testCrearNuevoVendedor() {
        Vendedor mockVendedor = new Vendedor(1, "Test Nombre", "Test Direccion", new Coordenada(10.0, 20.0));
        when(vendedorService.crearNuevoVendedor("Test Nombre", "Test Direccion", 10.0, 20.0)).thenReturn(mockVendedor);

        Vendedor result = vendedorController.crearNuevoVendedor("Test Nombre", "Test Direccion", 10.0, 20.0);

        assertNotNull(result);
        assertEquals(mockVendedor, result);
        verify(vendedorService, times(1)).crearNuevoVendedor("Test Nombre", "Test Direccion", 10.0, 20.0);
    }

    @Test
    void testModificarVendedor() {
        doNothing().when(vendedorService).modificarVendedor(1, "Nuevo Nombre", "Nueva Direccion", 15.0, 25.0);

        vendedorController.modificarVendedor(1, "Nuevo Nombre", "Nueva Direccion", 15.0, 25.0);

        verify(vendedorService, times(1)).modificarVendedor(1, "Nuevo Nombre", "Nueva Direccion", 15.0, 25.0);
    }

    @Test
    void testEliminarVendedor() {
        doNothing().when(vendedorService).eliminarVendedor(1);

        vendedorController.eliminarVendedor(1);

        verify(vendedorService, times(1)).eliminarVendedor(1);
    }

    @Test
    void testBuscarVendedor() {
        Vendedor mockVendedor = new Vendedor(1, "Test Nombre", "Test Direccion", new Coordenada(10.0, 20.0));
        when(vendedorService.buscarVendedor(1)).thenReturn(mockVendedor);

        Vendedor result = vendedorController.buscarVendedor(1);

        assertNotNull(result);
        assertEquals(mockVendedor, result);
        verify(vendedorService, times(1)).buscarVendedor(1);
    }

    @Test
    void testBuscarVendedorPorNombre() {
        List<Vendedor> mockVendedores = new ArrayList<>();
        when(vendedorService.buscarVendedorPorNombre("Test Nombre")).thenReturn(mockVendedores);

        List<Vendedor> result = vendedorController.buscarVendedorPorNombre("Test Nombre");

        assertNotNull(result);
        assertEquals(mockVendedores, result);
        verify(vendedorService, times(1)).buscarVendedorPorNombre("Test Nombre");
    }
}
