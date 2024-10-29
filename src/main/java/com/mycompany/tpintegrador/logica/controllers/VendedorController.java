package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import java.util.List;

public class VendedorController {

    private VendedorDao vendedorDao;

    public VendedorController(VendedorDao vendedorDao) {
        this.vendedorDao = vendedorDao;
    }

    public List<Vendedor> mostrarListaVendedor() {
        return vendedorDao.listarVendedores();
    }

    public Vendedor crearNuevoVendedor(String nombre, String direccion, double latitud, double longitud) {
        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setNombre(nombre);
        nuevoVendedor.setDireccion(direccion);
        nuevoVendedor.setCoordenada(new Coordenada(latitud, longitud));

        vendedorDao.crearVendedor(nuevoVendedor);
        return nuevoVendedor;
    }

    public void modificarVendedor(int id, String nombre, String direccion, double latitud, double longitud) {
        Vendedor vendedorExistente = vendedorDao.buscarVendedorPorId(id);
        if (vendedorExistente != null) {
            vendedorExistente.setNombre(nombre);
            vendedorExistente.setDireccion(direccion);
            vendedorExistente.setCoordenada(new Coordenada(latitud, longitud));
            vendedorDao.actualizarVendedor(vendedorExistente);
        } else {
            System.out.println("Vendedor con ID " + id + " no encontrado.");
        }
    }

    public void eliminarVendedor(int id) {
        Vendedor vendedor = vendedorDao.buscarVendedorPorId(id);
        if (vendedor != null) {
            vendedorDao.eliminarVendedor(id);
        } else {
            System.out.println("Vendedor con ID " + id + " no encontrado.");
        }
    }

    public Vendedor buscarVendedor(int id) {
        return vendedorDao.buscarVendedorPorId(id);
    }

    public List<Vendedor> buscarVendedorPorNombre(String nombre) {
        return vendedorDao.buscarVendedorPorNombre(nombre);  // Llamada al DAO para obtener la lista de vendedores por nombre
    }
}


