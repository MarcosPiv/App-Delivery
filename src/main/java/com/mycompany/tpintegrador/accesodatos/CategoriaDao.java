package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Categoria;

public interface CategoriaDao {
    void crearCategoria(Categoria categoria);
    Categoria buscarCategoria(int id);
    void actualizarCategoria(Categoria categoria);
    void eliminarCategoria(int id);
}
