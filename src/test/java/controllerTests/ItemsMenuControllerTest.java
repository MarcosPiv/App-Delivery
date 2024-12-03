package controllerTests;

import com.mycompany.tpintegrador.accesodatos.ItemsMenuDao;
import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.models.Categoria;
import com.mycompany.tpintegrador.logica.models.ItemMenu;
import com.mycompany.tpintegrador.logica.models.Bebida;
import com.mycompany.tpintegrador.logica.models.Comida;
import com.mycompany.tpintegrador.logica.controllers.ItemsMenuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class ItemsMenuControllerTest {

    @Mock
    private ItemsMenuDao itemsMenuDao;

    @Mock
    private CategoriaDao categoriaDao;

    @InjectMocks
    private ItemsMenuController itemsMenuController;

    private Categoria categoriaBebida;
    private Categoria categoriaComida;
    private Bebida bebida;
    private Comida comida;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        categoriaBebida = new Categoria(1, "Bebidas", "bebida");
        categoriaComida = new Categoria(2, "Comidas", "comida");

        bebida = new Bebida(1, 5.0, 500.0, "Cerveza", "Cerveza artesanal", 100.0, categoriaBebida, 550.0);
        comida = new Comida(2, 300, true, false, 250.0, "Ensalada", "Ensalada mixta", 80.0, categoriaComida, 300.0);
    }

    @Test
    public void testCrearNuevaCategoria() {
        ArgumentCaptor<Categoria> categoriaCaptor = ArgumentCaptor.forClass(Categoria.class);

        doNothing().when(categoriaDao).crearCategoria(any(Categoria.class));

        itemsMenuController.crearNuevaCategoria(1, "Bebidas", "bebida");

        verify(categoriaDao, times(1)).crearCategoria(categoriaCaptor.capture());

        Categoria categoriaCreada = categoriaCaptor.getValue();
        assertEquals(1, categoriaCreada.getId());
        assertEquals("Bebidas", categoriaCreada.getDescripcion());
        assertEquals("bebida", categoriaCreada.getTipoItem());
    }

    @Test
    public void testMostrarListaItemsMenu() {
        List<ItemMenu> items = Arrays.asList(bebida, comida);

        when(itemsMenuDao.listarItems()).thenReturn(items);

        List<ItemMenu> resultado = itemsMenuController.mostrarListaItemsMenu();

        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(bebida));
        assertTrue(resultado.contains(comida));

        verify(itemsMenuDao, times(1)).listarItems();
    }

    @Test
    public void testCrearNuevoItemMenu_CrearBebida() {
        when(categoriaDao.buscarCategoria(1)).thenReturn(categoriaBebida);
        doNothing().when(itemsMenuDao).crearItem(any(ItemMenu.class));

        ItemMenu nuevoItem = itemsMenuController.crearNuevoItemMenu(
                "Cerveza", "Cerveza artesanal", 100.0, 550.0, "bebida", 1, 5.0, 500.0,
                0, false, false, 0.0);

        assertNotNull(nuevoItem);
        assertInstanceOf(Bebida.class, nuevoItem);
        Bebida bebidaCreada = (Bebida) nuevoItem;
        assertEquals("Cerveza", bebidaCreada.getNombre());
        assertEquals(5.0, bebidaCreada.getGraduacionAlcoholica());
        assertEquals(500.0, bebidaCreada.getTamanio());
        assertEquals(categoriaBebida, bebidaCreada.getCategoria());

        verify(itemsMenuDao, times(1)).crearItem(any(Bebida.class));
    }

    @Test
    public void testCrearNuevoItemMenu_CrearComida() {
        when(categoriaDao.buscarCategoria(2)).thenReturn(categoriaComida);
        doNothing().when(itemsMenuDao).crearItem(any(ItemMenu.class));

        ItemMenu nuevoItem = itemsMenuController.crearNuevoItemMenu(
                "Ensalada", "Ensalada mixta", 80.0, 300.0, "comida", 2, 0.0, 0.0,
                300, true, false, 250.0);

        assertNotNull(nuevoItem);
        assertInstanceOf(Comida.class, nuevoItem);
        Comida comidaCreada = (Comida) nuevoItem;
        assertEquals("Ensalada", comidaCreada.getNombre());
        assertEquals(300, comidaCreada.getCalorias());
        assertTrue(comidaCreada.isAptoVegano());
        assertFalse(comidaCreada.isAptoCeliaco());
        assertEquals(250.0, comidaCreada.getPesoSinEnvase());
        assertEquals(categoriaComida, comidaCreada.getCategoria());

        verify(itemsMenuDao, times(1)).crearItem(any(Comida.class));
    }

    @Test
    public void testCrearNuevoItemMenu_CategoriaNoEncontrada() {
        when(categoriaDao.buscarCategoria(99)).thenReturn(null);

        ItemMenu nuevoItem = itemsMenuController.crearNuevoItemMenu(
                "Item desconocido", "Descripción", 50.0, 100.0, "bebida", 99, 0.0, 0.0,
                0, false, false, 0.0);

        assertNull(nuevoItem);

        verify(itemsMenuDao, never()).crearItem(any(ItemMenu.class));
    }

    @Test
    public void testModificarItemMenu_ModificarBebida() {
        when(itemsMenuDao.buscarItem(1)).thenReturn(bebida);
        when(categoriaDao.buscarCategoria(1)).thenReturn(categoriaBebida);

        itemsMenuController.modificarItemMenu(1, "Cerveza Modificada", "Cerveza artesanal modificada", 110.0, 560.0, "bebida", 1, 6.0, 550.0,
                0, false, false, 0.0);

        assertEquals("Cerveza Modificada", bebida.getNombre());
        assertEquals("Cerveza artesanal modificada", bebida.getDescripcion());
        assertEquals(110.0, bebida.getPrecio());
        assertEquals(560.0, bebida.getPeso());
        assertEquals(6.0, bebida.getGraduacionAlcoholica());
        assertEquals(550.0, bebida.getTamanio());
        assertEquals(categoriaBebida, bebida.getCategoria());

        verify(itemsMenuDao, times(1)).actualizarItem(bebida);
    }

    @Test
    public void testModificarItemMenu_ModificarComida() {
        when(itemsMenuDao.buscarItem(2)).thenReturn(comida);
        when(categoriaDao.buscarCategoria(2)).thenReturn(categoriaComida);

        itemsMenuController.modificarItemMenu(2, "Ensalada Modificada", "Ensalada mixta modificada", 85.0, 310.0, "comida", 2, 0.0, 0.0,
                320, false, true, 260.0);

        assertEquals("Ensalada Modificada", comida.getNombre());
        assertEquals("Ensalada mixta modificada", comida.getDescripcion());
        assertEquals(85.0, comida.getPrecio());
        assertEquals(310.0, comida.getPeso());
        assertEquals(320, comida.getCalorias());
        assertFalse(comida.isAptoVegano());
        assertTrue(comida.isAptoCeliaco());
        assertEquals(260.0, comida.getPesoSinEnvase());
        assertEquals(categoriaComida, comida.getCategoria());

        verify(itemsMenuDao, times(1)).actualizarItem(comida);
    }

    @Test
    public void testModificarItemMenu_ItemNoEncontrado() {
        when(itemsMenuDao.buscarItem(99)).thenReturn(null);

        itemsMenuController.modificarItemMenu(99, "Nombre", "Descripción", 0.0, 0.0, "bebida", 1, 0.0, 0.0,
                0, false, false, 0.0);

        verify(itemsMenuDao, never()).actualizarItem(any(ItemMenu.class));
    }

    @Test
    public void testModificarItemMenu_CategoriaNoEncontrada() {
        when(itemsMenuDao.buscarItem(1)).thenReturn(bebida);
        when(categoriaDao.buscarCategoria(99)).thenReturn(null);

        itemsMenuController.modificarItemMenu(1, "Cerveza Modificada", "Cerveza artesanal modificada", 110.0, 560.0, "bebida", 99, 6.0, 550.0,
                0, false, false, 0.0);

        // La categoría no debe cambiar si no se encuentra la nueva
        assertEquals(categoriaBebida, bebida.getCategoria());

        verify(itemsMenuDao, never()).actualizarItem(any(ItemMenu.class));
    }

    @Test
    public void testEliminarItemMenu_ItemExistente() {
        when(itemsMenuDao.buscarItem(1)).thenReturn(bebida);
        doNothing().when(itemsMenuDao).eliminarItem(1);

        itemsMenuController.eliminarItemMenu(1);

        verify(itemsMenuDao, times(1)).eliminarItem(1);
    }

    @Test
    public void testEliminarItemMenu_ItemNoExistente() {
        when(itemsMenuDao.buscarItem(99)).thenReturn(null);

        itemsMenuController.eliminarItemMenu(99);

        verify(itemsMenuDao, never()).eliminarItem(99);
    }

    @Test
    public void testBuscarItemMenu_ItemExistente() {
        when(itemsMenuDao.buscarItem(1)).thenReturn(bebida);

        ItemMenu resultado = itemsMenuController.buscarItemMenu(1);

        assertNotNull(resultado);
        assertEquals(bebida, resultado);
    }

    @Test
    public void testBuscarItemMenu_ItemNoExistente() {
        when(itemsMenuDao.buscarItem(99)).thenReturn(null);

        ItemMenu resultado = itemsMenuController.buscarItemMenu(99);

        assertNull(resultado);
    }
}
