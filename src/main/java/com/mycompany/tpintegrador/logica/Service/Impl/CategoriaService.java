package com.mycompany.tpintegrador.logica.Service.Impl;

import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.Service.ICategoriaService;
import com.mycompany.tpintegrador.logica.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    public Categoria crearNuevaCategoria(String descripcion, String tipoItem) {
        Categoria nuevaCategoria = new Categoria(0, descripcion, tipoItem);
        categoriaDao.save(nuevaCategoria);
        return nuevaCategoria;
    }

    public Categoria buscarCategoria(int id) {
        Optional<Categoria> categoria = categoriaDao.findById(id);
        return categoria.orElseThrow(() -> new RuntimeException("Categoría con ID " + id + " no encontrada."));
    }

    public void modificarCategoria(int id, String descripcion, String tipoItem) {

        Optional<Categoria> categoriaExistente = categoriaDao.findById(id);

        if (categoriaExistente.isPresent()) {
            categoriaExistente.get().setDescripcion(descripcion);
            categoriaExistente.get().setTipoItem(tipoItem);
            categoriaDao.save(categoriaExistente.get());
            System.out.println("Categoría actualizada: " + descripcion);

        } else {
            System.out.println("Categoría con ID " + id + " no encontrada.");

        }
    }
    public void eliminarCategoria(int id) {
        Optional<Categoria> categoria = categoriaDao.findById(id);
        if (categoria.isPresent()) {
            categoriaDao.deleteById(id);
            System.out.println("Categoría con ID " + id + " eliminada.");
        } else {
            System.out.println("Categoría con ID " + id + " no encontrada.");
        }
    }
}
