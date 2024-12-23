package com.mycompany.tpintegrador.logica.models;

import com.mycompany.tpintegrador.logica.strategy.PagarMercadoPago;
import com.mycompany.tpintegrador.logica.strategy.PagarTransferencia;
import com.mycompany.tpintegrador.logica.observer.Observer;
import com.mycompany.tpintegrador.logica.strategy.PagoStrategy;

import java.util.ArrayList;
import java.util.Date;

public class Cliente implements Observer {
    private int id;
    private String cuit;
    private String email;
    private String direccion;
    private Coordenada coordenada;
    private String nombre;

    public Cliente() {
    }

    public Cliente(int id, String cuit, String email, String direccion, Coordenada coordenada, String nombre) {
        this.id = id;
        this.cuit = cuit;
        this.email = email;
        this.direccion = direccion;
        this.coordenada = coordenada;
        this.nombre = nombre;
    }

    public static Cliente buscarClientePorNombre(ArrayList<Cliente> clientes, String nombre) {
        try {
            for (Cliente cliente : clientes) {
                if (cliente.getNombre().equalsIgnoreCase(nombre)) {
                    return cliente;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Se encontró un valor nulo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
        return null;
    }

    public static Cliente buscarClientePorId(ArrayList<Cliente> clientes, int id) {
        try {
            for (Cliente cliente : clientes) {
                if (cliente.getId() == id) {
                    return cliente;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Se encontró un valor nulo: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
        }
        return null;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void update(Pedido pedido) {
        if (pedido.getEstado() == Estado.EN_ENVIO) {
            generarPago(pedido);
        }
    }

    private void generarPago(Pedido pedido) {
        Pago pago = new Pago(pedido.getPrecioTotal(), new Date());
        if (pedido.getTipoDePago() instanceof PagarMercadoPago) {
            pago.setTipoPago("Mercado Pago");
        } else if (pedido.getTipoDePago() instanceof PagarTransferencia) {
            pago.setTipoPago("Transferencia");
        }
    }
}
