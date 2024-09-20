package com.mycompany.tpintegrador.respository;

import com.mycompany.tpintegrador.logica.DetallePedido;
import com.mycompany.tpintegrador.logica.Vendedor;
import com.mycompany.tpintegrador.logica.businessExceptions.ItemNoEncontradoException;

import java.util.List;

public interface ItemPedidoDao {
    //filtrado
    List<DetallePedido> filtrarPorCliente(String nombreCliente) throws ItemNoEncontradoException;
    List<DetallePedido> filtrarPorVendedor(String nombreVendedor) throws ItemNoEncontradoException;
    List<DetallePedido> filtrarPedidosPorEstado(String estado) throws ItemNoEncontradoException;
    List<DetallePedido> filtrarPorPrecioMinimo(double precioMin) throws ItemNoEncontradoException;
    List<DetallePedido> filtrarPorPrecioMaximo(double precioMax) throws ItemNoEncontradoException;
    List<DetallePedido> filtrarPorPrecioIgual(double precio) throws ItemNoEncontradoException;
    //ordenado
    List<DetallePedido> ordenarPorNombreCliente() throws ItemNoEncontradoException;
    List<DetallePedido> ordenarPorNombreVendedor() throws ItemNoEncontradoException;
    List<DetallePedido> ordenarPorIdCliente() throws ItemNoEncontradoException;
    List<DetallePedido> ordenarPorIdVendedor() throws ItemNoEncontradoException;
    //busqueda
    List<DetallePedido> buscarDetallePedidoPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException;
    List<DetallePedido> buscarDetallePedidoPorRestaurante(Vendedor vendedor) throws ItemNoEncontradoException;
}
