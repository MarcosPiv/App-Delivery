package com.mycompany.tpintegrador.respository.impl;
import com.mycompany.tpintegrador.respository.PagoStrategy;

public class PagarTransferencia extends PagoStrategy{
    private int cbu;
    private String cuit;

    public PagarTransferencia(int cbu , String cuit , Double importeTotal ){
        this.cbu=cbu;
        this.cuit=cuit;
        super.importe=this.pagar(importeTotal);
    } 

    @Override
    public double pagar(double unImporte){
        return (unImporte + (unImporte*0.02));
    };
}
