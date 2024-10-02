package com.mycompany.tpintegrador.logica;

public class PagarMercadoPago extends PagoStrategy{
    private String alias;

    public PagarMercadoPago(String alias, Double importeTotal){
        this.alias=alias;
        super.importe=this.pagar(importeTotal);
    }

    @Override
    public double pagar(double unImporte){
        return unImporte * 1.04;
    }
}
