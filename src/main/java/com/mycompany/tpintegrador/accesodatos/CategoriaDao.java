package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaDao extends CrudRepository<Categoria, Integer> {

    void actualizarCategoria(Optional<Categoria> categoriaExistente);
}
