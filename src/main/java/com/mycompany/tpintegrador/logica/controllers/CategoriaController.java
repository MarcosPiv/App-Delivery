package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.models.Categoria;

import java.util.List;

public class CategoriaController {

    private CategoriaDao categoriaDao;

    public CategoriaController(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }


    public Categoria crearNuevaCategoria(String descripcion, String tipoItem) {
        Categoria nuevaCategoria = new Categoria(0, descripcion, tipoItem);
        categoriaDao.crearCategoria(nuevaCategoria);
        return nuevaCategoria;
    }

    public Categoria buscarCategoria(int id) {
        Categoria categoria = categoriaDao.buscarCategoria(id);
        if (categoria == null) {
            System.out.println("Categoría con ID " + id + " no encontrada.");
        }
        return categoria;
    }

    public void modificarCategoria(int id, String descripcion, String tipoItem) {
        Categoria categoriaExistente = categoriaDao.buscarCategoria(id);
        if (categoriaExistente != null) {
            categoriaExistente.setDescripcion(descripcion);
            categoriaExistente.setTipoItem(tipoItem);
            categoriaDao.actualizarCategoria(categoriaExistente);
            System.out.println("Categoría actualizada: " + descripcion);
        } else {
            System.out.println("Categoría con ID " + id + " no encontrada.");
        }
    }

    public void eliminarCategoria(int id) {
        Categoria categoria = categoriaDao.buscarCategoria(id);
        if (categoria != null) {
            categoriaDao.eliminarCategoria(id);
            System.out.println("Categoría con ID " + id + " eliminada.");
        } else {
            System.out.println("Categoría con ID " + id + " no encontrada.");
        }
    }
}
