package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.CategoriaService;
import com.mycompany.tpintegrador.logica.controllers.CategoriaController;
import com.mycompany.tpintegrador.logica.models.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestCategoria {

    private CategoriaService categoriaService;
    private CategoriaController categoriaController;
    @BeforeEach
    void setUp() {
        categoriaService = Mockito.mock(CategoriaService.class);

        categoriaController = new CategoriaController(categoriaService);
    }

    @Test
    void testCrearNuevaCategoria() {

        String descripcion = "Comida";
        String tipoItem = "Principal";
        Categoria mockCategoria = new Categoria(1, descripcion, tipoItem);

        when(categoriaService.crearNuevaCategoria(descripcion, tipoItem)).thenReturn(mockCategoria);

        Categoria resultado = categoriaController.crearNuevaCategoria(descripcion, tipoItem);

        verify(categoriaService, times(1)).crearNuevaCategoria(descripcion, tipoItem);

        assertNotNull(resultado);
        assertEquals(mockCategoria, resultado);
    }

    @Test
    void testBuscarCategoria() {

        int id = 1;
        Categoria mockCategoria = new Categoria(id, "Comida", "Principal");

        when(categoriaService.buscarCategoria(id)).thenReturn(mockCategoria);

        Categoria resultado = categoriaController.buscarCategoria(id);

        verify(categoriaService, times(1)).buscarCategoria(id);

        assertNotNull(resultado);
        assertEquals(mockCategoria, resultado);
    }

    @Test
    void testModificarCategoria() {

        int id = 1;
        String nuevaDescripcion = "Bebida";
        String nuevoTipoItem = "Refresco";

        categoriaController.modificarCategoria(id, nuevaDescripcion, nuevoTipoItem);

        verify(categoriaService, times(1)).modificarCategoria(id, nuevaDescripcion, nuevoTipoItem);
    }

    @Test
    void testEliminarCategoria() {

        int id = 1;

        categoriaController.eliminarCategoria(id);

        verify(categoriaService, times(1)).eliminarCategoria(id);
    }
}
