package com.mycompany.tpintegrador.logica;

public class Bebida extends ItemMenu {
    private double graduacion_alcoholica;
    private double tamaño;

    public Bebida(double graduacion_alcoholica, double tamaño) {
        this.graduacion_alcoholica = graduacion_alcoholica;
        this.tamaño = tamaño;
    }

    public double getGraduacion_alcoholica() {
        return graduacion_alcoholica;
    }
    public void setGraduacion_alcoholica(double graduacion_alcoholica) {
        this.graduacion_alcoholica = graduacion_alcoholica;
    }
    public double getTamaño() {
        return tamaño;
    }
    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }
    
    @Override
    public double peso(){
        if(graduacion_alcoholica == 0){
            setPeso((this.graduacion_alcoholica*1.04)*1.2);
        }
        else{
            setPeso((this.graduacion_alcoholica*0.99)*1.2);
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
