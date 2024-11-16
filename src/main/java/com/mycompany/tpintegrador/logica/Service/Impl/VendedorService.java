package com.mycompany.tpintegrador.logica.Service.Impl;

import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.Service.IVendedorServicio;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorService implements IVendedorServicio {

    @Autowired
    private VendedorDao vendedorDao;

    public List<Vendedor> mostrarListaVendedor() {
        return (List<Vendedor>) vendedorDao.findAll();
    }

    public Vendedor crearNuevoVendedor(String nombre, String direccion, double latitud, double longitud) {
        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setNombre(nombre);
        nuevoVendedor.setDireccion(direccion);
        nuevoVendedor.setCoordenada(new Coordenada(latitud, longitud));

        vendedorDao.save(nuevoVendedor);
        return nuevoVendedor;
    }

    public void modificarVendedor(int id, String nombre, String direccion, double latitud, double longitud) {
        Optional<Vendedor> vendedorExistente = vendedorDao.findById(id);
        if (vendedorExistente.isPresent()) {
            Vendedor vendedorExis = vendedorExistente.get();
            vendedorExis.setNombre(nombre);
            vendedorExis.setDireccion(direccion);
            vendedorExis.setCoordenada(new Coordenada(latitud, longitud));
            vendedorDao.save(vendedorExis);
        } else {
            System.out.println("Vendedor con ID " + id + " no encontrado.");
        }
    }

    public void eliminarVendedor(int id) {
        Optional<Vendedor> vendedor = vendedorDao.findById(id);
        if (vendedor.isPresent()) {
            vendedorDao.deleteById(id);
        } else {
            System.out.println("Vendedor con ID " + id + " no encontrado.");
        }
    }

    public Vendedor buscarVendedor(int id) {
        Optional<Vendedor> vendedor = vendedorDao.findById(id);
        return vendedor.orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrada."));
    }

    public List<Vendedor> buscarVendedorPorNombre(String nombre) {
        return vendedorDao.findVendedorsByNombre(nombre);  // Llamada al DAO para obtener la lista de vendedores por nombre
    }
}
