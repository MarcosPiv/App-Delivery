package controllerTests;

import com.mycompany.tpintegrador.accesodatos.ClienteDao;
import com.mycompany.tpintegrador.accesodatos.PedidoDao;
import com.mycompany.tpintegrador.accesodatos.VendedorDao;
import com.mycompany.tpintegrador.logica.controllers.PedidoController;
import com.mycompany.tpintegrador.logica.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PedidoControllerTest {

    @Mock
    private PedidoDao pedidoDao;

    @Mock
    private ClienteDao clienteDao;

    @Mock
    private VendedorDao vendedorDao;

    @InjectMocks
    private PedidoController pedidoController;

    private Cliente cliente;
    private Vendedor vendedor;
    private Pedido pedido;
    private DetallePedido detallePedido;
    private List<DetallePedido> detalles;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks

        // Creación de objetos de prueba
        cliente = new Cliente(1, "20-12345678-9", "cliente@email.com", "Calle Ficticia 123",
                new Coordenada(40.7128, -74.0060), "Juan Pérez");

        vendedor = new Vendedor(1, "Restaurante Ejemplo", "Av. Siempre Viva 742",
                new Coordenada(40.7128, -74.0060));

        Comida comida = new Comida(1, 200, true, false, 150.0, "Pizza", "Pizza con queso", 500.0,
                new Categoria(1, "Comida", "Comida"), 0.5);

        // Inicializar detalles antes de crear el pedido
        detalles = new ArrayList<>();
        detallePedido = new DetallePedido(1, comida, 2, 1200, null);
        detalles.add(detallePedido);

        // Crear el pedido, ahora pasando la lista de detalles inicializada
        pedido = new Pedido(1, cliente, vendedor, 122.0, (ArrayList<DetallePedido>) detalles, Estado.PENDIENTE);

        // Configurar mocks
        when(clienteDao.buscarCliente(1)).thenReturn(cliente);
        when(vendedorDao.buscarVendedorPorId(1)).thenReturn(vendedor);
        when(pedidoDao.buscarPedido(1)).thenReturn(pedido);
    }

    @Test
    public void testListarPedidos() {
        // Simula que el DAO devuelve una lista de pedidos
        when(pedidoDao.listarPedidos()).thenReturn(Arrays.asList(pedido));

        List<Pedido> pedidos = pedidoController.listarPedidos();

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(pedido, pedidos.get(0));
        verify(pedidoDao, times(1)).listarPedidos();
    }

    @Test
    public void testCrearNuevoPedido() {
        doNothing().when(pedidoDao).crearPedido(any(Pedido.class));

        pedidoController.crearNuevoPedido(cliente, vendedor, detalles, Estado.PENDIENTE);

        verify(pedidoDao, times(1)).crearPedido(any(Pedido.class));
    }

    @Test
    public void testBuscarPedidoPorId() {
        when(pedidoDao.buscarPedido(1)).thenReturn(pedido);

        Pedido resultado = pedidoController.buscarPedidoPorId(1);

        assertNotNull(resultado);
        assertEquals(pedido, resultado);
        verify(pedidoDao, times(1)).buscarPedido(1);
    }

    @Test
    public void testActualizarPedido() {
        when(pedidoDao.buscarPedido(1)).thenReturn(pedido);
        doNothing().when(pedidoDao).actualizarPedido(any(Pedido.class));

        pedidoController.actualizarPedido(1, cliente, vendedor, detalles, Estado.RECIBIDO);

        assertEquals(Estado.RECIBIDO, pedido.getEstado());
        verify(pedidoDao, times(1)).actualizarPedido(any(Pedido.class));
    }

    @Test
    public void testEliminarPedido() {
        doNothing().when(pedidoDao).eliminarPedido(1);

        pedidoController.eliminarPedido(1);

        verify(pedidoDao, times(1)).eliminarPedido(1);
    }

    @Test
    public void testCambiarEstadoPedido() {
        when(pedidoDao.buscarPedido(1)).thenReturn(pedido);
        doNothing().when(pedidoDao).actualizarPedido(any(Pedido.class));

        pedidoController.cambiarEstadoPedido(1, Estado.RECIBIDO);

        assertEquals(Estado.RECIBIDO, pedido.getEstado());
        verify(pedidoDao, times(1)).actualizarPedido(any(Pedido.class));
    }

    @Test
    public void testMostrarDetallesPedido() {
        when(pedidoDao.buscarPedido(1)).thenReturn(pedido);

        List<DetallePedido> resultado = pedidoController.mostrarDetallesPedido(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(detallePedido, resultado.getFirst());
        verify(pedidoDao, times(1)).buscarPedido(1);
    }

    @Test
    public void testBuscarClientePorId() {
        when(clienteDao.buscarCliente(1)).thenReturn(cliente);

        Cliente resultado = pedidoController.buscarClientePorId(1);

        assertNotNull(resultado);
        assertEquals(cliente, resultado);
        verify(clienteDao, times(1)).buscarCliente(1);
    }

    @Test
    public void testBuscarVendedorPorId() {
        when(vendedorDao.buscarVendedorPorId(1)).thenReturn(vendedor);

        Vendedor resultado = pedidoController.buscarVendedorPorId(1);

        assertNotNull(resultado);
        assertEquals(vendedor, resultado);
        verify(vendedorDao, times(1)).buscarVendedorPorId(1);
    }
}
