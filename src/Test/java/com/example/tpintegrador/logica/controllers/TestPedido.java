package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.PedidoService;
import com.mycompany.tpintegrador.logica.controllers.PedidoController;
import com.mycompany.tpintegrador.logica.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestPedido {
    private PedidoService pedidoService;
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        // Crear un mock de PedidoService
        pedidoService = Mockito.mock(PedidoService.class);
        // Inicializar el PedidoController con el mock
        pedidoController = new PedidoController(pedidoService);
    }

    @Test
    void testListarPedidos() {
        // Preparar datos simulados
        Pedido pedido1 = mock(Pedido.class);
        Pedido pedido2 = mock(Pedido.class);
        List<Pedido> pedidosSimulados = new ArrayList<>();
        pedidosSimulados.add(pedido1);
        pedidosSimulados.add(pedido2);

        // Configurar el mock para devolver la lista simulada
        when(pedidoService.listarPedidos()).thenReturn(pedidosSimulados);

        // Llamar al método del controlador
        List<Pedido> resultado = pedidoController.listarPedidos();

        // Verificar resultados
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        // Verificar interacción con el mock
        verify(pedidoService, times(1)).listarPedidos();
    }

    @Test
    void testCrearNuevoPedido() {
        // Preparar datos simulados
        Cliente cliente = mock(Cliente.class);
        Vendedor vendedor = mock(Vendedor.class);
        DetallePedido detalle1 = mock(DetallePedido.class);
        DetallePedido detalle2 = mock(DetallePedido.class);
        List<DetallePedido> detalles = new ArrayList<>();
        detalles.add(detalle1);
        detalles.add(detalle2);
        Estado estado = Estado.PENDIENTE;

        pedidoController.crearNuevoPedido(cliente, vendedor, detalles, estado);

        verify(pedidoService, times(1)).crearNuevoPedido(cliente, vendedor, detalles, estado);
    }

    @Test
    void testEliminarPedido() {

        pedidoController.eliminarPedido(1);

        verify(pedidoService, times(1)).eliminarPedido(1);
    }

    @Test
    void testCambiarEstadoPedido() {

        pedidoController.cambiarEstadoPedido(1, Estado.EN_PREPARACION);

        verify(pedidoService, times(1)).cambiarEstadoPedido(1, Estado.EN_PREPARACION);
    }

    @Test
    void testMostrarDetallesPedido() {

        DetallePedido detalle1 = mock(DetallePedido.class);
        DetallePedido detalle2 = mock(DetallePedido.class);
        List<DetallePedido> detallesSimulados = new ArrayList<>();
        detallesSimulados.add(detalle1);
        detallesSimulados.add(detalle2);

        when(pedidoService.mostrarDetallesPedido(1)).thenReturn(detallesSimulados);

        List<DetallePedido> resultado = pedidoController.mostrarDetallesPedido(1);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(pedidoService, times(1)).mostrarDetallesPedido(1);
    }

    @Test
    void testBuscarVendedorPorId() {

        Vendedor vendedorSimulado = mock(Vendedor.class);
        when(pedidoService.buscarVendedorPorId(1)).thenReturn(vendedorSimulado);

        Vendedor resultado = pedidoController.buscarVendedorPorId(1);

        assertNotNull(resultado);

        verify(pedidoService, times(1)).buscarVendedorPorId(1);
    }

    @Test
    void testBuscarClientePorId() {

        Cliente clienteSimulado = mock(Cliente.class);
        when(pedidoService.buscarClientePorId(1)).thenReturn(clienteSimulado);

        Cliente resultado = pedidoController.buscarClientePorId(1);

        assertNotNull(resultado);

        verify(pedidoService, times(1)).buscarClientePorId(1);
    }

    @Test
    void testBuscarPedidoPorId() {

        Pedido pedidoSimulado = mock(Pedido.class);
        when(pedidoService.buscarPedidoPorId(1)).thenReturn(pedidoSimulado);

        Pedido resultado = pedidoController.buscarPedidoPorId(1);

        assertNotNull(resultado);

        verify(pedidoService, times(1)).buscarPedidoPorId(1);
    }
}
