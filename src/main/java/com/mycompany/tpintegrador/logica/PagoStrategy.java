package com.mycompany.tpintegrador.logica;

public abstract class PagoStrategy {
    protected double importe;

    public abstract double pagar(double unImporte);

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
