package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.logica.models.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    private PedidoDao pedidoDao;

    public PedidoController(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }


    public List<Pedido> listarPedidos() {
        return pedidoDao.listarPedidos();
    }


    public void crearNuevoPedido(Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(vendedor);
        nuevoPedido.setEstado(estado);
        nuevoPedido.setDetallesPedido((ArrayList<DetallePedido>) detalles);
        nuevoPedido.setPrecioTotal(nuevoPedido.calcularPrecioTotal());

        pedidoDao.crearPedido(nuevoPedido);
    }


    public Pedido buscarPedidoPorId(int id) {
        return pedidoDao.buscarPedido(id);
    }


    public void actualizarPedido(int id, Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado) {
        Pedido pedidoExistente = pedidoDao.buscarPedido(id);

        if (pedidoExistente != null) {
            pedidoExistente.setCliente(cliente);
            pedidoExistente.setRestaurante(vendedor);
            pedidoExistente.setDetallesPedido((ArrayList<DetallePedido>) detalles);
            pedidoExistente.setEstado(estado);
            pedidoExistente.setPrecioTotal(pedidoExistente.calcularPrecioTotal());

            pedidoDao.actualizarPedido(pedidoExistente);
        }
    }


    public void eliminarPedido(int id) {
        pedidoDao.eliminarPedido(id);
    }


    public void cambiarEstadoPedido(int id, Estado nuevoEstado) {
        Pedido pedidoExistente = pedidoDao.buscarPedido(id);

        if (pedidoExistente != null) {
            pedidoExistente.setEstado(nuevoEstado);
            pedidoDao.actualizarPedido(pedidoExistente);
        }
    }


    public List<DetallePedido> mostrarDetallesPedido(int id) {
        Pedido pedidoExistente = pedidoDao.buscarPedido(id);
        if (pedidoExistente != null) {
            return pedidoExistente.getDetallesPedido();
        }
        return null;
    }
}

