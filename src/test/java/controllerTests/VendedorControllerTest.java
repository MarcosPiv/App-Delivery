package controllerTests;

import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import com.mycompany.tpintegrador.logica.models.Vendedor;
import com.mycompany.tpintegrador.logica.controllers.VendedorController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VendedorControllerTest {

    @Mock
    private VendedorDao vendedorDao; // Mock del DAO

    @InjectMocks
    private VendedorController vendedorController; // Se inyecta el mock en el controlador

    private Vendedor vendedor1;
    private Vendedor vendedor2;

    @BeforeEach
    public void setUp() {
        // Inicializar los mocks de Mockito
        MockitoAnnotations.openMocks(this);

        vendedor1 = new Vendedor();
        vendedor1.setId(1);
        vendedor1.setNombre("Juan Pérez");
        vendedor1.setDireccion("Calle Falsa 123");
        vendedor1.setCoordenada(new Coordenada(40.7128, -74.0060));

        vendedor2 = new Vendedor();
        vendedor2.setId(2);
        vendedor2.setNombre("María Gómez");
        vendedor2.setDireccion("Avenida Siempre Viva 742");
        vendedor2.setCoordenada(new Coordenada(34.0522, -118.2437));
    }

    @Test
    public void testMostrarListaVendedor() {
        // Simular que el DAO devuelve una lista de vendedores
        List<Vendedor> vendedores = Arrays.asList(vendedor1, vendedor2);
        when(vendedorDao.listarVendedores()).thenReturn(vendedores);

        List<Vendedor> resultado = vendedorController.mostrarListaVendedor();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertTrue(resultado.contains(vendedor1));
        assertTrue(resultado.contains(vendedor2));

        verify(vendedorDao, times(1)).listarVendedores();
    }

    @Test
    public void testCrearNuevoVendedor() {
        // Capturar el objeto Vendedor que se pasa al DAO
        ArgumentCaptor<Vendedor> vendedorCaptor = ArgumentCaptor.forClass(Vendedor.class);

        doNothing().when(vendedorDao).crearVendedor(any(Vendedor.class));

        Vendedor nuevoVendedor = vendedorController.crearNuevoVendedor(
                "Carlos López", "Boulevard de los Sueños 456", 51.5074, -0.1278);

        assertNotNull(nuevoVendedor);
        assertEquals("Carlos López", nuevoVendedor.getNombre());
        assertEquals("Boulevard de los Sueños 456", nuevoVendedor.getDireccion());
        assertEquals(51.5074, nuevoVendedor.getCoordenada().getLat());
        assertEquals(-0.1278, nuevoVendedor.getCoordenada().getLng());

        verify(vendedorDao, times(1)).crearVendedor(vendedorCaptor.capture());

        Vendedor vendedorCreado = vendedorCaptor.getValue();
        assertEquals("Carlos López", vendedorCreado.getNombre());
        assertEquals("Boulevard de los Sueños 456", vendedorCreado.getDireccion());
        assertEquals(51.5074, vendedorCreado.getCoordenada().getLat());
        assertEquals(-0.1278, vendedorCreado.getCoordenada().getLng());
    }

    @Test
    public void testModificarVendedor() {
        // Simular que el vendedor existe
        when(vendedorDao.buscarVendedorPorId(1)).thenReturn(vendedor1);
        doNothing().when(vendedorDao).actualizarVendedor(any(Vendedor.class));

        vendedorController.modificarVendedor(1, "Juan Actualizado", "Calle Nueva 789", 41.0000, -75.0000);

        assertEquals("Juan Actualizado", vendedor1.getNombre());
        assertEquals("Calle Nueva 789", vendedor1.getDireccion());
        assertEquals(41.0000, vendedor1.getCoordenada().getLat());
        assertEquals(-75.0000, vendedor1.getCoordenada().getLng());

        verify(vendedorDao, times(1)).buscarVendedorPorId(1);
        verify(vendedorDao, times(1)).actualizarVendedor(vendedor1);
    }

    @Test
    public void testModificarVendedorNoExistente() {
        // Simular que el vendedor no existe
        when(vendedorDao.buscarVendedorPorId(99)).thenReturn(null);

        vendedorController.modificarVendedor(99, "Nombre Inexistente", "Dirección Inexistente", 0.0, 0.0);

        verify(vendedorDao, times(1)).buscarVendedorPorId(99);
        verify(vendedorDao, never()).actualizarVendedor(any(Vendedor.class));
    }

    @Test
    public void testEliminarVendedor() {
        // Simular que el vendedor existe
        when(vendedorDao.buscarVendedorPorId(1)).thenReturn(vendedor1);
        doNothing().when(vendedorDao).eliminarVendedor(1);

        vendedorController.eliminarVendedor(1);

        verify(vendedorDao, times(1)).buscarVendedorPorId(1);
        verify(vendedorDao, times(1)).eliminarVendedor(1);
    }

    @Test
    public void testEliminarVendedorNoExistente() {
        // Simular que el vendedor no existe
        when(vendedorDao.buscarVendedorPorId(99)).thenReturn(null);

        vendedorController.eliminarVendedor(99);

        verify(vendedorDao, times(1)).buscarVendedorPorId(99);
        verify(vendedorDao, never()).eliminarVendedor(99);
    }

    @Test
    public void testBuscarVendedor() {
        // Simular que el vendedor existe
        when(vendedorDao.buscarVendedorPorId(1)).thenReturn(vendedor1);

        Vendedor resultado = vendedorController.buscarVendedor(1);

        assertNotNull(resultado);
        assertEquals(vendedor1, resultado);

        verify(vendedorDao, times(1)).buscarVendedorPorId(1);
    }

    @Test
    public void testBuscarVendedorNoExistente() {
        // Simular que el vendedor no existe
        when(vendedorDao.buscarVendedorPorId(99)).thenReturn(null);

        Vendedor resultado = vendedorController.buscarVendedor(99);

        assertNull(resultado);

        verify(vendedorDao, times(1)).buscarVendedorPorId(99);
    }

    @Test
    public void testBuscarVendedorPorNombre() {
        // Simular que el DAO devuelve una lista de vendedores con el nombre buscado
        List<Vendedor> vendedores = Collections.singletonList(vendedor1);
        when(vendedorDao.buscarVendedorPorNombre("Juan Pérez")).thenReturn(vendedores);

        List<Vendedor> resultado = vendedorController.buscarVendedorPorNombre("Juan Pérez");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(vendedor1));

        verify(vendedorDao, times(1)).buscarVendedorPorNombre("Juan Pérez");
    }

    @Test
    public void testBuscarVendedorPorNombreSinResultados() {
        // Simular que el DAO no encuentra vendedores con el nombre buscado
        when(vendedorDao.buscarVendedorPorNombre("Nombre Inexistente")).thenReturn(List.of());

        List<Vendedor> resultado = vendedorController.buscarVendedorPorNombre("Nombre Inexistente");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(vendedorDao, times(1)).buscarVendedorPorNombre("Nombre Inexistente");
    }
}
