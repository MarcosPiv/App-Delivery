package com.mycompany.tpintegrador.logica.models;

public class Bebida extends ItemMenu {
    private double graduacionAlcoholica;
    private double tamanio;

    public Bebida(int id,double graduacionAlcoholica, double tamanio,String nombre, String descripcion, double precio,Categoria categoria, double peso  ) {
        super.setId(id);
        super.setNombre(nombre);
        super.setDescripcion(descripcion);
        super.setPrecio(precio);
        super.setPeso(peso);
        super.setCategoria(categoria);
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
