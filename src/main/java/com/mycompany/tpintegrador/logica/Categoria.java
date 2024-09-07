package com.mycompany.tpintegrador.logica;

public class Categoria{
    private int id;
    private String descripcion;
    private ItemMenu tipo_item;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getTipo_item() {
        return tipo_item;
    }
    public void setTipo_item(String tipo_item) {
        this.tipo_item = tipo_item;
    }
}
