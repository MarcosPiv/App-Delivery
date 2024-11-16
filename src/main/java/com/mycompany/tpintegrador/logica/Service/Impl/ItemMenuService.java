package com.mycompany.tpintegrador.logica.Service.Impl;

import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.logica.Service.IitemMenuService;
import com.mycompany.tpintegrador.logica.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemMenuService implements IitemMenuService {

    @Autowired
    private ItemsMenuDao itemsMenuDao;
    @Autowired
    private CategoriaDao categoriaDao;

    public void crearNuevaCategoria(int id, String descripcion, String tipoItem) {
        Categoria nuevaCategoria = new Categoria(id, descripcion, tipoItem);
        categoriaDao.save(nuevaCategoria);
        System.out.println("Categoría creada: " + descripcion + " con ID: " + id);
    }

    public List<ItemMenu> mostrarListaItemsMenu() {
        return (List<ItemMenu>) itemsMenuDao.findAll();
    }

    public ItemMenu crearNuevoItemMenu(String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase) {
        ItemMenu nuevoItemMenu;
        Optional<Categoria> categoria = categoriaDao.findById(categoriaId);

        if (categoria.isEmpty()) {
            System.out.println("Categoría con ID " + categoriaId + " no encontrada.");
            return null;
        }

        if ("bebida".equalsIgnoreCase(tipo)) {
            nuevoItemMenu = new Bebida(0, graduacionAlcoholica, tamanio, nombre, descripcion, precio, categoria.orElse(null), peso);
        } else {
            nuevoItemMenu = new Comida(0, calorias, aptoVegano, aptoCeliaco, pesoSinEnvase, nombre, descripcion, precio, categoria.orElse(null), peso);
        }

        itemsMenuDao.save(nuevoItemMenu);
        return nuevoItemMenu;
    }

    public void modificarItemMenu(int id, String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase) {
        Optional<ItemMenu> itemExistente = itemsMenuDao.findById(id);

        if (itemExistente.isPresent()) {
            ItemMenu itemMenuExistente = itemExistente.get();

            itemMenuExistente.setNombre(nombre);
            itemMenuExistente.setDescripcion(descripcion);
            itemMenuExistente.setPrecio(precio);
            itemMenuExistente.setPeso(peso);

            Optional<Categoria> categoria = categoriaDao.findById(categoriaId);
            if (categoria.isPresent()) {
                Categoria categoriaExistente = categoria.get();
                itemMenuExistente.setCategoria(categoriaExistente);
            } else {
                System.out.println("Categoría con ID " + categoriaId + " no encontrada.");
                return;
            }

            if (itemMenuExistente instanceof Bebida bebida) {
                bebida.setGraduacionAlcoholica(graduacionAlcoholica);
                bebida.setTamanio(tamanio);
            } else if (itemMenuExistente instanceof Comida comida) {
                comida.setCalorias(calorias);
                comida.setAptoVegano(aptoVegano);
                comida.setAptoCeliaco(aptoCeliaco);
                comida.setPesoSinEnvase(pesoSinEnvase);
            }

            itemsMenuDao.save(itemMenuExistente);
        } else {
            System.out.println("Ítem con ID " + id + " no encontrado.");
        }
    }

    public void eliminarItemMenu(int id) {
        Optional<ItemMenu> itemMenu = itemsMenuDao.findById(id);
        if (itemMenu.isPresent()) {
            itemsMenuDao.deleteById(id);
        } else {
            System.out.println("Ítem con ID " + id + " no encontrado.");
        }
    }

    public ItemMenu buscarItemMenu(int id) {
        Optional<ItemMenu> itemMenu = itemsMenuDao.findById(id);
        return itemMenu.orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrada."));
    }
}
