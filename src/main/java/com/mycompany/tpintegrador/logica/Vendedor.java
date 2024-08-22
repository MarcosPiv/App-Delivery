package com.mycompany.tpintegrador.logica;

import java.util.ArrayList;

public class Vendedor {
    private int id;
    private String nombre;
    private String direccion;
    private Coordenada coordenada;
    
    public Vendedor(int id, String nombre, String direccion, Coordenada coordenada) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.coordenada = coordenada;
    }
    
    public static Vendedor buscarVendedorPorNombre(ArrayList<Vendedor> vendedores, String nombre) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombre)) {
                return vendedor;
            }
        }
        return null;
    }

    public static Vendedor buscarVendedorPorId(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getId() == id) {
                return vendedor;
            }
        }
        return null;
    }
    
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", coordenada=" + coordenada + '}';
    }
    
    public double calcularDistancia(Cliente otra) {
        //fórmula de Haversine
        final int RadioTierra = 6371;
        double dLat = Math.toRadians(otra.getCoordenada().getLat() - coordenada.getLat());
        double dLng = Math.toRadians(otra.getCoordenada().getLng() - coordenada.getLng());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(coordenada.getLat())) * Math.cos(Math.toRadians(otra.getCoordenada().getLat())) *
                   Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RadioTierra * c;
    }
    
    
}
