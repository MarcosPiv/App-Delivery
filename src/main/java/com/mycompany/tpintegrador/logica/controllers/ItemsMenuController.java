package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.models.Categoria;
import com.mycompany.tpintegrador.logica.models.ItemMenu;
import com.mycompany.tpintegrador.logica.models.Bebida;
import com.mycompany.tpintegrador.logica.models.Comida;

import java.util.List;

public class ItemsMenuController {

    private ItemsMenuDao itemsMenuDao;
    private CategoriaDao categoriaDao;

    public ItemsMenuController(ItemsMenuDao itemsMenuDao, CategoriaDao categoriaDao) {
        this.itemsMenuDao = itemsMenuDao;
        this.categoriaDao = categoriaDao;
    }

    public void crearNuevaCategoria(int id, String descripcion, String tipoItem) {
        Categoria nuevaCategoria = new Categoria(id, descripcion, tipoItem);
        categoriaDao.crearCategoria(nuevaCategoria);
        System.out.println("Categoría creada: " + descripcion + " con ID: " + id);
    }

    public List<ItemMenu> mostrarListaItemsMenu() {
        return itemsMenuDao.listarItems();
    }

    public ItemMenu crearNuevoItemMenu(String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase) {
        ItemMenu nuevoItemMenu;

        Categoria categoria = categoriaDao.buscarCategoria(categoriaId);
        if (categoria == null) {
            System.out.println("Categoría con ID " + categoriaId + " no encontrada.");
            return null;
        }

        if ("bebida".equalsIgnoreCase(tipo)) {
            nuevoItemMenu = new Bebida(0, graduacionAlcoholica, tamanio, nombre, descripcion, precio, categoria, peso);
        } else {
            nuevoItemMenu = new Comida(0, calorias, aptoVegano, aptoCeliaco, pesoSinEnvase, nombre, descripcion, precio, categoria, peso);
        }

        itemsMenuDao.crearItem(nuevoItemMenu);
        return nuevoItemMenu;
    }

    public void modificarItemMenu(int id, String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase) {
        ItemMenu itemExistente = itemsMenuDao.buscarItem(id);
        if (itemExistente != null) {
            itemExistente.setNombre(nombre);
            itemExistente.setDescripcion(descripcion);
            itemExistente.setPrecio(precio);
            itemExistente.setPeso(peso);

            Categoria categoria = categoriaDao.buscarCategoria(categoriaId);
            if (categoria != null) {
                itemExistente.setCategoria(categoria);
            } else {
                System.out.println("Categoría con ID " + categoriaId + " no encontrada.");
                return;
            }

            if (itemExistente instanceof Bebida) {
                Bebida bebida = (Bebida) itemExistente;
                bebida.setGraduacionAlcoholica(graduacionAlcoholica);
                bebida.setTamanio(tamanio);
            } else if (itemExistente instanceof Comida) {
                Comida comida = (Comida) itemExistente;
                comida.setCalorias(calorias);
                comida.setAptoVegano(aptoVegano);
                comida.setAptoCeliaco(aptoCeliaco);
                comida.setPesoSinEnvase(pesoSinEnvase);
            }

            itemsMenuDao.actualizarItem(itemExistente);
        } else {
            System.out.println("Ítem con ID " + id + " no encontrado.");
        }
    }

    public void eliminarItemMenu(int id) {
        ItemMenu itemMenu = itemsMenuDao.buscarItem(id);
        if (itemMenu != null) {
            itemsMenuDao.eliminarItem(id);
        } else {
            System.out.println("Ítem con ID " + id + " no encontrado.");
        }
    }

    public ItemMenu buscarItemMenu(int id) {
        return itemsMenuDao.buscarItem(id);
    }
}
