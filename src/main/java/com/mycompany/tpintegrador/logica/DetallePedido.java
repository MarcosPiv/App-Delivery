package com.mycompany.tpintegrador.logica;

public class DetallePedido {
    private int id;
    private ItemMenu item;
    private Pedido pedido;
    private int cantidad;
    private double precio;

    @Override
    public String toString() {
        return "DetallePedido{" +
                "id=" + id +
                ", item=" + item.getNombre() +
                ", pedido=" + pedido.getRestaurante().getNombre() +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }

    public DetallePedido(int id, ItemMenu item, Pedido pedido, int cantidad, double precio) {
        this.id = id;
        this.item = item;
        this.pedido = pedido;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemMenu getItem() {
        return item;
    }

    public void setItem(ItemMenu item) {
        this.item = item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
