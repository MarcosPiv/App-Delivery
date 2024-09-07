package com.mycompany.tpintegrador.logica;

public class Comida extends ItemMenu {

    private int calorias;
    private boolean aptoVegano;
    private boolean aptoCeliaco;

    
    public Comida(int calorias, boolean aptoVegano, boolean aptoCeliaco) {
        this.calorias = calorias;
        this.aptoVegano = aptoVegano;
        this.aptoCeliaco = aptoCeliaco;
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

    public double peso(){
        return ((this.getPeso()*1.1)*1.2);
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

    public boolean aptoCeliaco() {
        return aptoCeliaco;
    }
}
    

