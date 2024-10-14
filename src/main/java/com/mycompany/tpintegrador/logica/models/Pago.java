package com.mycompany.tpintegrador.logica.models;

import java.util.Date;

public class Pago {
    private double monto;
    private Date fecha;
    private String tipoPago;

    public Pago(double monto, Date fecha) {
        this.monto = monto;
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
}
