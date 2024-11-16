package com.mycompany.tpintegrador.logica.Service;

import com.mycompany.tpintegrador.logica.models.ItemMenu;

import java.util.List;

public interface IitemMenuService {

    void crearNuevaCategoria(int id, String descripcion, String tipoItem);

    List<ItemMenu> mostrarListaItemsMenu();

    ItemMenu crearNuevoItemMenu(String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase);

    void modificarItemMenu(int id, String nombre, String descripcion, double precio, double peso, String tipo, int categoriaId, double graduacionAlcoholica, double tamanio, int calorias, boolean aptoVegano, boolean aptoCeliaco, double pesoSinEnvase);

    void eliminarItemMenu(int id);

    ItemMenu buscarItemMenu(int id);
}
