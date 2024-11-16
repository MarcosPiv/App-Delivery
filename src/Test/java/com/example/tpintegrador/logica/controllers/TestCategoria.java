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
        // Crear un mock de CategoriaService
        categoriaService = Mockito.mock(CategoriaService.class);

        // Inicializar el controlador con el servicio mockeado
        categoriaController = new CategoriaController(categoriaService);
    }

    @Test
    void testCrearNuevaCategoria() {
        // Preparar datos de prueba
        String descripcion = "Comida";
        String tipoItem = "Principal";
        Categoria mockCategoria = new Categoria(1, descripcion, tipoItem);

        // Configurar el comportamiento del mock
        when(categoriaService.crearNuevaCategoria(descripcion, tipoItem)).thenReturn(mockCategoria);

        // Llamar al método a probar
        Categoria resultado = categoriaController.crearNuevaCategoria(descripcion, tipoItem);

        // Verificar que el servicio fue llamado con los parámetros correctos
        verify(categoriaService, times(1)).crearNuevaCategoria(descripcion, tipoItem);

        // Validar el resultado
        assertNotNull(resultado);
        assertEquals(mockCategoria, resultado);
    }

    @Test
    void testBuscarCategoria() {
        // Preparar datos de prueba
        int id = 1;
        Categoria mockCategoria = new Categoria(id, "Comida", "Principal");

        // Configurar el comportamiento del mock
        when(categoriaService.buscarCategoria(id)).thenReturn(mockCategoria);

        // Llamar al método a probar
        Categoria resultado = categoriaController.buscarCategoria(id);

        // Verificar que el servicio fue llamado con los parámetros correctos
        verify(categoriaService, times(1)).buscarCategoria(id);

        // Validar el resultado
        assertNotNull(resultado);
        assertEquals(mockCategoria, resultado);
    }

    @Test
    void testModificarCategoria() {
        // Preparar datos de prueba
        int id = 1;
        String nuevaDescripcion = "Bebida";
        String nuevoTipoItem = "Refresco";

        // Llamar al método a probar
        categoriaController.modificarCategoria(id, nuevaDescripcion, nuevoTipoItem);

        // Verificar que el servicio fue llamado con los parámetros correctos
        verify(categoriaService, times(1)).modificarCategoria(id, nuevaDescripcion, nuevoTipoItem);
    }

    @Test
    void testEliminarCategoria() {
        // Preparar datos de prueba
        int id = 1;

        // Llamar al método a probar
        categoriaController.eliminarCategoria(id);

        // Verificar que el servicio fue llamado con los parámetros correctos
        verify(categoriaService, times(1)).eliminarCategoria(id);
    }
}
