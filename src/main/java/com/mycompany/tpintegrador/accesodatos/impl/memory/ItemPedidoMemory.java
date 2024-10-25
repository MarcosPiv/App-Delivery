package com.mycompany.tpintegrador.accesodatos.impl.memory;

import com.mycompany.tpintegrador.logica.models.Pedido;
import com.mycompany.tpintegrador.logica.businessExceptions.ItemNoEncontradoException;
import com.mycompany.tpintegrador.accesodatos.ItemPedidoDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



public class ItemPedidoMemory implements ItemPedidoDao {
    private List<Pedido> listaDePedidos;

    public ItemPedidoMemory() {
        List<Pedido> listaDePedidos = new ArrayList<>();
    }

    public ItemPedidoMemory(List<Pedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
    }

    public void addPedido(Pedido unPedido){
        listaDePedidos.add(unPedido);
    }

    @Override
    public List<Pedido> filtrarPedidosPorEstado(String estado) throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .filter(pedido -> pedido.getEstado().toString().equalsIgnoreCase(estado))
                .toList();
        if(pedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems para el estado: " + estado + ".");
        }
        return pedidosFiltrados;
    }

    @Override
        public List<Pedido> filtrarPorPrecioMinimo(double precioMin) throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                    .filter(pedido -> pedido.getPrecioTotal() >= precioMin)
                    .toList();
            if(pedidosFiltrados.isEmpty()){
                throw new ItemNoEncontradoException("No se encontraron ítems mayores a: " + precioMin + ".");
            }
            return pedidosFiltrados;
        }

    @Override
    public List<Pedido> filtrarPorPrecioMaximo(double precioMax) throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .filter(pedido -> pedido.getPrecioTotal() <= precioMax)
                .toList();
        if(pedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems menores a: " + precioMax + ".");
        }
        return pedidosFiltrados;
    }

    @Override
    public List<Pedido> ordenarPorPrecio() throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .sorted(Comparator.comparingDouble(Pedido::getPrecioTotal))
                .toList();
        if(pedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return pedidosFiltrados;
    }

    @Override
    public List<Pedido> ordenarPorNombre() throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .sorted(Comparator.comparing(pedido -> pedido.getRestaurante().getNombre()))
                .toList();
        if(pedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return pedidosFiltrados;
    }

    @Override
    public List<Pedido> ordenarPorId() throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .sorted(Comparator.comparingInt(Pedido::getId))
                .toList();
        if(pedidosFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return pedidosFiltrados;
    }

    @Override
    public List<Pedido> buscarItemPedidoPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .filter(pedido -> pedido.getPrecioTotal() >= precioMin && pedido.getPrecioTotal() <= precioMax)
                .toList();
        if (pedidosFiltrados.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems en el rango de precios: " + precioMin + " - " + precioMax + ".");
        }
        return pedidosFiltrados;
    }

    @Override
    public List<Pedido> buscarItemPedidoPorRestaurante(String unNombreVendedor) throws ItemNoEncontradoException {
        List<Pedido> pedidosFiltrados = listaDePedidos.stream()
                .filter(pedido -> pedido.getRestaurante().getNombre() == unNombreVendedor)
                .toList();
        if (pedidosFiltrados.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems del restaurante " + unNombreVendedor + ".");
        }
        return pedidosFiltrados;
    }

}
