package com.mycompany.tpintegrador.respository.impl;

import com.mycompany.tpintegrador.logica.DetallePedido;
import com.mycompany.tpintegrador.logica.Vendedor;
import com.mycompany.tpintegrador.logica.businessExceptions.ItemNoEncontradoException;
import com.mycompany.tpintegrador.respository.ItemPedidoDao;

import java.util.*;


public class ItemPedidoMemory implements ItemPedidoDao {
    private List<DetallePedido> listaDeDetallesPedidos;

    public ItemPedidoMemory() {
        List<DetallePedido> listaDeDetallesPedidos = new ArrayList<>();
    }

    public ItemPedidoMemory(List<DetallePedido> listaDePedidos) {
        this.listaDeDetallesPedidos = listaDePedidos;
    }

    @Override
    public List<DetallePedido> filtrarPorCliente(String nombreCliente) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPedido().getCliente().getNombre().equalsIgnoreCase(nombreCliente))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems para el cliente: " + nombreCliente + ".");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> filtrarPorVendedor(String nombreVendedor) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPedido().getRestaurante().getNombre().equalsIgnoreCase(nombreVendedor))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems para el vendedor: " + nombreVendedor + ".");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> filtrarPedidosPorEstado(String estado) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPedido().getEstado().toString().equalsIgnoreCase(estado))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems para el estado: " + estado + ".");
        }
        return itemsFiltrados;
    }

    @Override
        public List<DetallePedido> filtrarPorPrecioMinimo(double precioMin) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                    .filter(item -> item.getPrecio() > precioMin)
                    .toList();
            if(itemsFiltrados.isEmpty()){
                throw new ItemNoEncontradoException("No se encontraron ítems mayores a: " + precioMin + ".");
            }
            return itemsFiltrados;
        }

    @Override
    public List<DetallePedido> filtrarPorPrecioMaximo(double precioMax) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPrecio() < precioMax)
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems menores a: " + precioMax + ".");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> filtrarPorPrecioIgual(double precioMax) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPrecio() == precioMax)
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems iguales a: " + precioMax + ".");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> ordenarPorNombreCliente() throws ItemNoEncontradoException {
        Set<String> nombresClientesUnicos = new HashSet<>();
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> nombresClientesUnicos.add(item.getPedido().getCliente().getNombre()))
                .sorted(Comparator.comparing(items -> items.getPedido().getCliente().getNombre()))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> ordenarPorNombreVendedor() throws ItemNoEncontradoException {
        Set<String> nombresVendedoresUnicos = new HashSet<>();
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> nombresVendedoresUnicos.add(item.getPedido().getRestaurante().getNombre()))
                .sorted(Comparator.comparing(items -> items.getPedido().getRestaurante().getNombre()))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> ordenarPorIdCliente() throws ItemNoEncontradoException {
        Set<Integer> idClientesUnicos = new HashSet<>();
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> idClientesUnicos.add(item.getPedido().getCliente().getId()))
                .sorted(Comparator.comparingInt(items -> items.getPedido().getCliente().getId()))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> ordenarPorIdVendedor() throws ItemNoEncontradoException {
        Set<Integer> idVendedoresUnicos = new HashSet<>();
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> idVendedoresUnicos.add(item.getPedido().getRestaurante().getId()))
                .sorted(Comparator.comparingInt(items -> items.getPedido().getRestaurante().getId()))
                .toList();
        if(itemsFiltrados.isEmpty()){
            throw new ItemNoEncontradoException("No se encontraron ítems.");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> buscarDetallePedidoPorRangoPrecio(double precioMin, double precioMax) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(item -> item.getPrecio() >= precioMin && item.getPrecio() <= precioMax)
                .toList();
        if (itemsFiltrados.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems en el rango de precios: " + precioMin + " - " + precioMax + ".");
        }
        return itemsFiltrados;
    }

    @Override
    public List<DetallePedido> buscarDetallePedidoPorRestaurante(Vendedor vendedor) throws ItemNoEncontradoException {
        List<DetallePedido> itemsFiltrados = listaDeDetallesPedidos.stream()
                .filter(items -> items.getPedido().getRestaurante().equals(vendedor))
                .toList();
        if (itemsFiltrados.isEmpty()) {
            throw new ItemNoEncontradoException("No se encontraron ítems del restaurante " + vendedor.getNombre() + ".");
        }
        return itemsFiltrados;
    }

}
