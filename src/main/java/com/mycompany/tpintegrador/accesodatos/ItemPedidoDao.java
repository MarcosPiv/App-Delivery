package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Pedido;
import com.mycompany.tpintegrador.logica.businessExceptions.ItemNoEncontradoException;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemPedidoDao extends CrudRepository<Pedido, Integer> {
    List<Pedido> filtrarPedidosPorEstado(String estado) throws ItemNoEncontradoException;
    List<Pedido> filtrarPorPrecioMinimo(double precioMin) throws ItemNoEncontradoException;
    List<Pedido> filtrarPorPrecioMaximo(double precioMax) throws ItemNoEncontradoException;
    List<Pedido> ordenarPorPrecio() throws ItemNoEncontradoException;
    List<Pedido> ordenarPorNombre() throws ItemNoEncontradoException;
    List<Pedido> ordenarPorId() throws ItemNoEncontradoException;
    List<Pedido> buscarItemPedidoPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException;
    List<Pedido> buscarItemPedidoPorRestaurante(String unNombreVendedor) throws ItemNoEncontradoException;
}
