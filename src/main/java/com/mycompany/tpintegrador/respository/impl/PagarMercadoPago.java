package com.mycompany.tpintegrador.respository.impl;
import com.mycompany.tpintegrador.respository.PagoStrategy;

public class PagarMercadoPago extends PagoStrategy{
    private String alias;

    public PagarMercadoPago(String alias, Double importeTotal){
        this.alias=alias;
        super.importe=this.pagar(importeTotal);
    }

    @Override
    public double pagar(double unImporte){
        return (unImporte+ (unImporte*0.04));
    }
}
