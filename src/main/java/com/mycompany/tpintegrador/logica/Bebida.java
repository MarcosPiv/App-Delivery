package com.mycompany.tpintegrador.logica;

public class Bebida extends ItemMenu {
    private double graduacionAlcoholica;
    private double tamanio;

    public Bebida(double graduacionAlcoholica, double tamanio) {
        this.graduacionAlcoholica = graduacionAlcoholica;
        this.tamanio = tamanio;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }
    public void setGraduacionAlcoholica(double graduacionAlcoholica) {
        this.graduacionAlcoholica = graduacionAlcoholica;
    }
    public double getTamanio() {
        return tamanio;
    }
    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }
    
    @Override
    public double peso(){
        if(graduacionAlcoholica == 0){
            setPeso((this.graduacionAlcoholica*1.04)*1.2);
        }
        else{
            setPeso((this.graduacionAlcoholica*0.99)*1.2);
        }
        return getPeso();
    }
    @Override
    public boolean esComida() {
        return false;
    }

    @Override
    public boolean esBebida() {
        return true;
    }

    @Override
    public boolean aptoVegano() {
        return false;
    }
}
