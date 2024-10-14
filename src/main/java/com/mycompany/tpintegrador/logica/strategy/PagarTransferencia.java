package com.mycompany.tpintegrador.logica.strategy;

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
        return unImporte * 1.02;
    };
}
