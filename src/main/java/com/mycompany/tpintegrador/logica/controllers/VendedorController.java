package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.VendedorService;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import java.util.List;

public class VendedorController {

    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService){
        this.vendedorService = vendedorService;
    }

    public List<Vendedor> mostrarListaVendedor(){
        return vendedorService.mostrarListaVendedor();
    }

    public Vendedor crearNuevoVendedor(String nombre, String direccion, double latitud, double longitud){
        return vendedorService.crearNuevoVendedor(nombre, direccion, latitud, longitud);
    }

    public void modificarVendedor(int id, String nombre, String direccion, double latitud, double longitud){
        vendedorService.modificarVendedor(id, nombre, direccion, latitud, longitud);
    }

    public void eliminarVendedor(int id){
        vendedorService.eliminarVendedor(id);
    }

    public Vendedor buscarVendedor(int id){
        return vendedorService.buscarVendedor(id);
    }

    public List<Vendedor> buscarVendedorPorNombre(String nombre){
        return vendedorService.buscarVendedorPorNombre(nombre);
    }
}


