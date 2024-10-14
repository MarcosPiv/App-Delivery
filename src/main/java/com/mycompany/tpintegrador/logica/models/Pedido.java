package com.mycompany.tpintegrador.logica.models;

import com.mycompany.tpintegrador.logica.strategy.PagarMercadoPago;
import com.mycompany.tpintegrador.logica.strategy.PagarTransferencia;
import com.mycompany.tpintegrador.logica.strategy.PagoStrategy;
import com.mycompany.tpintegrador.logica.observer.Observable;
import com.mycompany.tpintegrador.logica.observer.Observer;

import java.util.ArrayList;

public class Pedido extends Observable {
    private int id;
    private Cliente cliente;
    private Vendedor restaurante;
    private double precioTotal;
    private ArrayList<DetallePedido> detallesPedido;
    private Estado estado;
    private PagoStrategy tipoDePago;
    private ArrayList<Observer> observers = new ArrayList<>();

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
    public PagoStrategy getTipoDePago() {
        return tipoDePago;
    }

    public void setTipoDePago(PagoStrategy tipoDePago) {
        this.tipoDePago = tipoDePago;
    }

    public void pagarMercadoPago(String alias,Double importeTotal){
        tipoDePago = new PagarMercadoPago(alias,importeTotal);
    }

    public void pagarTransferencia(int cbu,String cuit,Double importeTotal){
        tipoDePago = new PagarTransferencia(cbu,cuit,importeTotal);
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

    public double calcularPrecioTotal() {
        double total = 0;
        try {
            for (DetallePedido unDetalle : detallesPedido) {
                if (unDetalle == null) {
                    throw new NullPointerException("DetallePedido es nulo");
                }
                double totalPorDetalle = unDetalle.getCantidad() * unDetalle.getPrecio();
                total = total + totalPorDetalle;
            }
        } catch (NullPointerException e) {
            System.err.println("Se encontró un valor nulo: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println("Error aritmético: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
        return total;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
