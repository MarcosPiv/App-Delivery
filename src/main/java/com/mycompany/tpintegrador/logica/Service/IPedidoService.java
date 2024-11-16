package com.mycompany.tpintegrador.logica.Service;

import com.mycompany.tpintegrador.logica.models.*;
import java.util.List;

public interface IPedidoService {

    List<Pedido> listarPedidos();

    void crearNuevoPedido(Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado);

    void actualizarPedido(int id, Cliente cliente, Vendedor vendedor, List<DetallePedido> detalles, Estado estado);

    void eliminarPedido(int id);

    void cambiarEstadoPedido(int id, Estado nuevoEstado);

    List<DetallePedido> mostrarDetallesPedido(int id);

    Vendedor buscarVendedorPorId(int vendedorId);

    Cliente buscarClientePorId(int clienteId);
}
