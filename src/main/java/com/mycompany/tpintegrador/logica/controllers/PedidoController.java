package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.PedidoService;
import com.mycompany.tpintegrador.logica.models.*;
import java.util.List;

public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    public List<Pedido> listarPedidos(){
        return pedidoService.listarPedidos();
    }

    public void crearNuevoPedido(Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado){
        pedidoService.crearNuevoPedido(cliente, vendedor, detalles, estado);
    }

    public void eliminarPedido(int id){
        pedidoService.eliminarPedido(id);
    }

    public void cambiarEstadoPedido(int id, Estado nuevoEstado){
        pedidoService.cambiarEstadoPedido(id, nuevoEstado);
    }

    public List<DetallePedido> mostrarDetallesPedido(int id){
        return pedidoService.mostrarDetallesPedido(id);
    }

    public Vendedor buscarVendedorPorId(int vendedorId){
        return pedidoService.buscarVendedorPorId(vendedorId);
    }

    public Cliente buscarClientePorId(int clienteId){
        return pedidoService.buscarClientePorId(clienteId);
    }

    public Pedido buscarPedidoPorId(int id){
        return pedidoService.buscarPedidoPorId(id);
    }
}

