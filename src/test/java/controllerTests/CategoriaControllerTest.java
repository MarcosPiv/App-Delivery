package controllerTests;

import com.mycompany.tpintegrador.accesodatos.CategoriaDao;
import com.mycompany.tpintegrador.logica.models.Categoria;
import com.mycompany.tpintegrador.logica.controllers.CategoriaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaControllerTest {

    @Mock
    private CategoriaDao categoriaDao; // Mock del DAO

    @InjectMocks
    private CategoriaController categoriaController; // Se inyecta el mock en el controlador

    private Categoria categoria;

    @BeforeEach
    public void setUp() {
        // Inicializar los mocks de Mockito
        MockitoAnnotations.openMocks(this);

        categoria = new Categoria(1, "Descripción de ejemplo", "Tipo de Item");
    }

    @Test
    public void testCrearNuevaCategoria() {
        // Simula que el método crearCategoria no hace nada (es un método void)
        doNothing().when(categoriaDao).crearCategoria(any(Categoria.class));

        Categoria nuevaCategoria = categoriaController.crearNuevaCategoria(
                "Descripción de ejemplo", "Tipo de Item");
        assertNotNull(nuevaCategoria);
        assertEquals("Descripción de ejemplo", nuevaCategoria.getDescripcion());
        assertEquals("Tipo de Item", nuevaCategoria.getTipoItem());

        verify(categoriaDao, times(1)).crearCategoria(any(Categoria.class));
    }

    @Test
    public void testBuscarCategoria() {
        // Simula que buscarCategoria devuelve una categoría específica
        when(categoriaDao.buscarCategoria(1)).thenReturn(categoria);
        Categoria resultado = categoriaController.buscarCategoria(1);
        assertNotNull(resultado);
        assertEquals(categoria.getId(), resultado.getId());
        assertEquals(categoria.getDescripcion(), resultado.getDescripcion());
        assertEquals(categoria.getTipoItem(), resultado.getTipoItem());
    }

    @Test
    public void testModificarCategoria() {
        // Simulamos que la categoría existe y es devuelta por el DAO
        when(categoriaDao.buscarCategoria(1)).thenReturn(categoria);
        categoriaController.modificarCategoria(1, "Descripción modificada", "Nuevo Tipo de Item");

        assertEquals("Descripción modificada", categoria.getDescripcion());
        assertEquals("Nuevo Tipo de Item", categoria.getTipoItem());

        verify(categoriaDao, times(1)).actualizarCategoria(categoria);
    }

    @Test
    public void testEliminarCategoria() {
        // Simulamos que la categoría existe y es devuelta por el DAO
        when(categoriaDao.buscarCategoria(1)).thenReturn(categoria);
        categoriaController.eliminarCategoria(1);
        verify(categoriaDao, times(1)).eliminarCategoria(1);
    }

    @Test
    public void testEliminarCategoriaNoExistente() {
        // Simulamos que la categoría no existe
        when(categoriaDao.buscarCategoria(1)).thenReturn(null);
        categoriaController.eliminarCategoria(1);
        verify(categoriaDao, never()).eliminarCategoria(1);
    }
}

