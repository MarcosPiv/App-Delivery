package com.mycompany.tpintegrador.logica;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Vendedor restaurante;
    private double precioTotal;
    private ArrayList<DetallePedido> detallesPedido;
    private Estado estado;

    public Pedido() {
    detallesPedido = new ArrayList<>();
    }

    public Pedido(int id, Cliente cliente, Vendedor restaurante, double precioTotal, ArrayList<DetallePedido> detallesPedido, Estado estado) {
        this.id = id;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.precioTotal = precioTotal;
        this.detallesPedido = detallesPedido;
        this.estado = estado;
    }

    public void agregarDetalle(DetallePedido detalle){
        detallesPedido.add(detalle);
    }

    public void quitarDetalle(DetallePedido detalle){
        detallesPedido.remove(detalle);
    }

    public Vendedor getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Vendedor restaurante) {
        this.restaurante = restaurante;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public ArrayList<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(ArrayList<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }
}
