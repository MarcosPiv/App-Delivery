package com.mycompany.tpintegrador.logica.Service;

import com.mycompany.tpintegrador.logica.models.Categoria;

public interface ICategoriaService {

    Categoria crearNuevaCategoria(String descripcion, String tipoItem);

    Categoria buscarCategoria(int id);

    void modificarCategoria(int id, String descripcion, String tipoItem);

    void eliminarCategoria(int id);
}
