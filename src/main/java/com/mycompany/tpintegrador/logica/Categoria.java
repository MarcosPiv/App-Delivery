package com.mycompany.tpintegrador.logica;

public class Categoria{
    private int id;
    private String descripcion;
    private ItemMenu tipoItem;

    public Categoria(int id, String descripcion, ItemMenu tipoItem) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipoItem = tipoItem;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ItemMenu getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(ItemMenu tipoItem) {
        this.tipoItem = tipoItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
