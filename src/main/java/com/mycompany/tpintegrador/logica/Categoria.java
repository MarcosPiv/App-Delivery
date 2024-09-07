package com.mycompany.tpintegrador.logica;

public class Categoria{
    private int id;
    private String descripcion;
    private ItemMenu tipo_item;

    public Categoria(int id, String descripcion, ItemMenu tipo_item) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo_item = tipo_item;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ItemMenu getTipo_item() {
        return tipo_item;
    }

    public void setTipo_item(ItemMenu tipo_item) {
        this.tipo_item = tipo_item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
