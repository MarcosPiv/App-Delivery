package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.models.ItemMenu;
import com.mycompany.tpintegrador.logica.Service.Impl.ItemMenuService;
import java.util.List;

public class ItemsMenuController {

    private final ItemMenuService itemMenuService;

    public ItemsMenuController(ItemMenuService itemMenuService){
        this.itemMenuService = itemMenuService;
    }

    public void crearNuevaCategoria(int id, String descripcion, String tipoItem){
        itemMenuService.crearNuevaCategoria(id, descripcion, tipoItem);
    }

    public List<ItemMenu> mostrarListaItemsMenu(){
        return itemMenuService.mostrarListaItemsMenu();
    }

    public ItemMenu crearNuevoItemMenu(String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase){
        return itemMenuService.crearNuevoItemMenu(nombre, descripcion, precio, peso, tipo, categoriaId, graduacionAlcoholica, tamanio, calorias, aptoVegano, aptoCeliaco, pesoSinEnvase);
    }

    public void modificarItemMenu(int id, String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase){
        itemMenuService.modificarItemMenu(id, nombre, descripcion, precio, peso, tipo, categoriaId, graduacionAlcoholica, tamanio, calorias, aptoVegano, aptoCeliaco, pesoSinEnvase);
    }

    public void eliminarItemMenu(int id){
        itemMenuService.eliminarItemMenu(id);
    }

    public ItemMenu buscarItemMenu(int id){
        return itemMenuService.buscarItemMenu(id);
    }

}
