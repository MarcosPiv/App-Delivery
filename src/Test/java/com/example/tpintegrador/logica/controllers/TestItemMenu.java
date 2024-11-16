package com.example.tpintegrador.logica.controllers;

import com.mycompany.tpintegrador.logica.Service.Impl.ItemMenuService;
import com.mycompany.tpintegrador.logica.models.ItemMenu;
import com.mycompany.tpintegrador.logica.controllers.ItemsMenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestItemMenu {
    private ItemMenuService itemMenuService;
    private ItemsMenuController itemsMenuController;

    @BeforeEach
    void setUp() {

        itemMenuService = Mockito.mock(ItemMenuService.class);

        itemsMenuController = new ItemsMenuController(itemMenuService);
    }

    @Test
    void testCrearNuevaCategoria() {

        itemsMenuController.crearNuevaCategoria(1, "Postres", "Comida");

        verify(itemMenuService, times(1)).crearNuevaCategoria(1, "Postres", "Comida");
    }

    @Test
    void testMostrarListaItemsMenu() {

        ItemMenu item1 = mock(ItemMenu.class);
        ItemMenu item2 = mock(ItemMenu.class);
        List<ItemMenu> itemsSimulados = new ArrayList<>();
        itemsSimulados.add(item1);
        itemsSimulados.add(item2);

        when(itemMenuService.mostrarListaItemsMenu()).thenReturn(itemsSimulados);

        List<ItemMenu> resultado = itemsMenuController.mostrarListaItemsMenu();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        verify(itemMenuService, times(1)).mostrarListaItemsMenu();
    }

    @Test
    void testCrearNuevoItemMenu() {

        ItemMenu itemSimulado = mock(ItemMenu.class);
        when(itemMenuService.crearNuevoItemMenu("Pizza", "Deliciosa pizza", 15.0, 0.5, "Comida", 1, 0.0, 0.0, 800, false, false, 0.4))
                .thenReturn(itemSimulado);

        ItemMenu resultado = itemsMenuController.crearNuevoItemMenu("Pizza", "Deliciosa pizza", 15.0, 0.5, "Comida", 1, 0.0, 0.0, 800, false, false, 0.4);

        assertNotNull(resultado);

        verify(itemMenuService, times(1))
                .crearNuevoItemMenu("Pizza", "Deliciosa pizza", 15.0, 0.5, "Comida", 1, 0.0, 0.0, 800, false, false, 0.4);
    }

    @Test
    void testModificarItemMenu() {

        itemsMenuController.modificarItemMenu(1, "Pizza", "Pizza de pepperoni", 20.0, 0.6, "Comida", 2, 0.0, 0.0, 850, false, true, 0.5);

        verify(itemMenuService, times(1))
                .modificarItemMenu(1, "Pizza", "Pizza de pepperoni", 20.0, 0.6, "Comida", 2, 0.0, 0.0, 850, false, true, 0.5);
    }

    @Test
    void testEliminarItemMenu() {

        itemsMenuController.eliminarItemMenu(1);

        verify(itemMenuService, times(1)).eliminarItemMenu(1);
    }

    @Test
    void testBuscarItemMenu() {

        ItemMenu itemSimulado = mock(ItemMenu.class);
        when(itemMenuService.buscarItemMenu(1)).thenReturn(itemSimulado);

        ItemMenu resultado = itemsMenuController.buscarItemMenu(1);

        assertNotNull(resultado);

        verify(itemMenuService, times(1)).buscarItemMenu(1);
    }
}
