package com.mycompany.tpintegrador.logica.Service.Impl;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoDao pedidoDao;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private VendedorDao vendedorDao;


    public List<Pedido> listarPedidos() {
        return (List<Pedido>) pedidoDao.findAll();
    }

    public void crearNuevoPedido(Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado) {
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCliente(cliente);
        nuevoPedido.setRestaurante(vendedor);
        nuevoPedido.setEstado(estado);
        nuevoPedido.setDetallesPedido((ArrayList<DetallePedido>) detalles);
        nuevoPedido.setPrecioTotal(nuevoPedido.calcularPrecioTotal());

        pedidoDao.save(nuevoPedido);
    }


    public Pedido buscarPedidoPorId(int id) {
        Optional<Pedido> pedido = pedidoDao.findById(id);
        return pedido.orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrada."));
    }


    public void actualizarPedido(int id, Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado) {
        Optional<Pedido> pedidoExistente = pedidoDao.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedidoExis = pedidoExistente.get();
            pedidoExis.setCliente(cliente);
            pedidoExis.setRestaurante(vendedor);
            pedidoExis.setDetallesPedido((ArrayList<DetallePedido>) detalles);
            pedidoExis.setEstado(estado);
            pedidoExis.setPrecioTotal(pedidoExis.calcularPrecioTotal());

            pedidoDao.save(pedidoExis);
        }
    }


    public void eliminarPedido(int id) {
        pedidoDao.deleteById(id);
    }


    public void cambiarEstadoPedido(int id, Estado nuevoEstado) {
        Optional<Pedido> pedidoExistente = pedidoDao.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedidoExis = pedidoExistente.get();
            pedidoExis.setEstado(nuevoEstado);
            pedidoDao.save(pedidoExis);
        }
    }


    public List<DetallePedido> mostrarDetallesPedido(int id) {

        Optional<Pedido> pedidoExistente = pedidoDao.findById(id);

        if (pedidoExistente.isPresent()) {
            return pedidoExistente.get().getDetallesPedido();
        }
        return null;
    }

    public Vendedor buscarVendedorPorId(int vendedorId) {
        Optional<Vendedor> itemMenu = vendedorDao.findById(vendedorId);
        return itemMenu.orElseThrow(() -> new RuntimeException("Cliente con ID " + vendedorId + " no encontrada."));
    }

    public Cliente buscarClientePorId(int clienteId) {
        Optional<Cliente> itemMenu = clienteDao.findById(clienteId);
        return itemMenu.orElseThrow(() -> new RuntimeException("Cliente con ID " + clienteId + " no encontrada."));
    }

}
