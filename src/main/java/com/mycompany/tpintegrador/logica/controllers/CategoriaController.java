package com.mycompany.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.CategoriaService;
import com.mycompany.tpintegrador.logica.models.Categoria;

public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Categoria crearNuevaCategoria(String descripcion, String tipoItem){
        return categoriaService.crearNuevaCategoria(descripcion, tipoItem);
    }

    public Categoria buscarCategoria(int id) {
        return categoriaService.buscarCategoria(id);
    }

    public void modificarCategoria(int id, String descripcion, String tipoItem) {
        categoriaService.modificarCategoria(id, descripcion, tipoItem);
    }

    public void eliminarCategoria(int id) {
        categoriaService.eliminarCategoria(id);
    }
}
