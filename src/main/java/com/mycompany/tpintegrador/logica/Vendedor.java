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

    public double distancia(Cliente cliente) {
        //la idea salio de https://gist.github.com/vananth22/888ed9a22105670e7a4092bdcf0d72e4

        final int R = 6371; // Radio de la Tierra en kilómetros

        double lat1 = this.coordenada.getLat();
        double lon1 = this.coordenada.getLat();
        double lat2 = cliente.getCoordenada().getLat();
        double lon2 = cliente.getCoordenada().getLat();

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Convertir a kilómetros

        return distance;
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
    
}
