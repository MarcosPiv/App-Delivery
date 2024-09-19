package com.mycompany.tpintegrador.logica;

public class DetallePedido {
    private int id;
    private ItemMenu item;
    private Pedido pedido;
    private int cantidad;
    private double precio;

    public DetallePedido(int id, ItemMenu item, Pedido pedido, int cantidad, double precio) {
        this.id = id;
        this.item = item;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }

}
