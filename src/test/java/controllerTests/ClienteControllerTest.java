package controllerTests;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.logica.models.Cliente;
import com.mycompany.tpintegrador.logica.models.Coordenada;
import com.mycompany.tpintegrador.logica.controllers.ClienteController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteControllerTest {

    @Mock
    private ClienteDao clienteDao; // Mock del DAO

    @InjectMocks
    private ClienteController clienteController; // Se inyecta el mock en el controlador

    private Cliente cliente;
    private Coordenada coordenada;

    @BeforeEach
    public void setUp() {
        // Inicializar los mocks de Mockito
        MockitoAnnotations.openMocks(this);

        coordenada = new Coordenada(40.7128, -74.0060);
        cliente = new Cliente(1, "20-12345678-9", "cliente@email.com", "Calle Ficticia 123", coordenada, "Juan Pérez");
    }

    @Test
    public void testCrearNuevoCliente() {
        // Simula que el metodo crearCliente no hace nada (es un método void)
        doNothing().when(clienteDao).crearCliente(any(Cliente.class));

        Cliente nuevoCliente = clienteController.crearNuevoCliente(
                "20-12345678-9", "cliente@email.com", "Calle Ficticia 123",
                40.7128, -74.0060, "Juan Pérez");
        assertNotNull(nuevoCliente);
        assertEquals("Juan Pérez", nuevoCliente.getNombre());

        verify(clienteDao, times(1)).crearCliente(any(Cliente.class));
    }

    @Test
    public void testBuscarCliente() {
        // Simula que buscarCliente devuelve un cliente específico
        when(clienteDao.buscarCliente(1)).thenReturn(cliente);
        Cliente resultado = clienteController.buscarCliente(1);
        assertNotNull(resultado);
        assertEquals(cliente.getId(), resultado.getId());
        assertEquals(cliente.getNombre(), resultado.getNombre());
    }

    @Test
    public void testModificarCliente() {
        // Simulamos que el cliente existe y es devuelto por el DAO
        when(clienteDao.buscarCliente(1)).thenReturn(cliente);
        clienteController.modificarCliente(1, "20-12345678-9", "nuevoemail@email.com", "Nueva Dirección",
                40.7128, -74.0061, "Juan Pérez Modificado");

        assertEquals("nuevoemail@email.com", cliente.getEmail());
        assertEquals("Nueva Dirección", cliente.getDireccion());
        assertEquals(40.7128, cliente.getCoordenada().getLat());
        assertEquals(-74.0061, cliente.getCoordenada().getLng());
        assertEquals("Juan Pérez Modificado", cliente.getNombre());

        verify(clienteDao, times(1)).actualizarCliente(cliente);
    }

    @Test
    public void testEliminarCliente() {
        // Simulamos que el cliente existe y es devuelto por el DAO
        when(clienteDao.buscarCliente(1)).thenReturn(cliente);
        clienteController.eliminarCliente(1);
        verify(clienteDao, times(1)).eliminarCliente(1);
    }

    @Test
    public void testEliminarClienteNoExistente() {
        // Simulamos que el cliente no existe
        when(clienteDao.buscarCliente(1)).thenReturn(null);
        clienteController.eliminarCliente(1);
        verify(clienteDao, never()).eliminarCliente(1);
    }
}
