package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Pedido;

import java.util.List;

public interface PedidoDao {
    List<Pedido> listarPedidos();
    void crearPedido(Pedido pedido);
    void actualizarPedido(Pedido pedido);
    void eliminarPedido(int id);
    Pedido buscarPedido(int id);
}
