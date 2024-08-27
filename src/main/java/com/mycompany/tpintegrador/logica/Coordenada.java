package com.mycompany.tpintegrador.logica;

public class Coordenada  {
    private double lat;
    private double lng;

    public Coordenada(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "Coordenada{" + "lat=" + lat + ", lng=" + lng + '}';
    }
 
}

