package com.mycompany.tpintegrador.logica;

public class Categoria{
    private int id;
    private String descripcion;
    private String tipoItem /*Bebida o Comida*/;

    public Categoria(int id, String descripcion, String tipoItem) {
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

    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
