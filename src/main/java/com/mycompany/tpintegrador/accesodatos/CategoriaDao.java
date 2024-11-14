package com.mycompany.tpintegrador.accesodatos;

import com.mycompany.tpintegrador.logica.models.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaDao extends CrudRepository<Categoria, Integer> {

}
