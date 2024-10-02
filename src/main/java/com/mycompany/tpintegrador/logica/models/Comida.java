package com.mycompany.tpintegrador.logica.models;

public class Comida extends ItemMenu {

    private int calorias;
    private boolean aptoVegano;
    private boolean aptoCeliaco;
    private double pesoSinEnvase;

    public Comida(int id,int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase,String nombre, String descripcion, double precio,Categoria categoria, double peso  ) {
        super.setId(id);
        super.setNombre(nombre);
        super.setDescripcion(descripcion);
        super.setPrecio(precio);
        super.setPeso(peso);
        super.setCategoria(categoria);
        this.calorias = calorias;
        this.aptoVegano = aptoVegano;
        this.aptoCeliaco = aptoCeliaco;
        this.pesoSinEnvase = pesoSinEnvase;
    }

    public double getPesoSinEnvase() {
        return pesoSinEnvase;
    }
    public void setPesoSinEnvase(double pesoSinEnvase) {
        this.pesoSinEnvase = pesoSinEnvase;
    }
    public int getCalorias() {
        return calorias;
    }
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
    public boolean isAptoVegano() {
        return aptoVegano;
    }
    public void setAptoVegano(boolean aptoVegano) {
        this.aptoVegano = aptoVegano;
    }
    public boolean isAptoCeliaco() {
        return aptoCeliaco;
    }
    public void setAptoCeliaco(boolean aptoCeliaco) {
        this.aptoCeliaco = aptoCeliaco;
    }

    @Override
    public double peso(){
        setPeso(this.pesoSinEnvase*1.1);
        return getPeso();
    }

    @Override
    public boolean esComida() {
        return true;
    }

    @Override
    public boolean esBebida() {
        return false;
    }

    @Override
    public boolean aptoVegano() {
        return aptoVegano;
    }

}
    

