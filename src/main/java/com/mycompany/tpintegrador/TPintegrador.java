package com.mycompany.tpintegrador;

import com.mycompany.tpintegrador.accesodatos.impl.jdbc.*;
import com.mycompany.tpintegrador.config.DatabaseConnection;
import com.mycompany.tpintegrador.logica.controllers.*;
import com.mycompany.tpintegrador.logica.models.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TPintegrador {

    public static void main(String[] args) {

        try {
            // Conectar a la base de datos
            Connection connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            }

            // Instanciar los controladores
            VendedorController vendedorController = new VendedorController(new VendedorJDBC(connection));
            ClienteController clienteController = new ClienteController(new ClienteJDBC(connection));
            ItemsMenuController itemsMenuController = new ItemsMenuController(new ItemsMenuJDBC(connection),new CategoriaJDBC(connection));
            PedidoController pedidoController = new PedidoController(new PedidoJDBC(connection));
            CategoriaController categoriaController = new CategoriaController(new CategoriaJDBC(connection));
            // CASO DE PRUEBA 1: VendedorController - CRUD
            System.out.println("\n--- CASO DE PRUEBA 1: VendedorController ---");

            // Crear un nuevo vendedor
            Vendedor nuevVendedor= vendedorController.crearNuevoVendedor("Vendedor Prueba", "Calle Falsa 123", -34.603722, -58.381592);
            System.out.println("Vendedor creado: Vendedor Prueba");

            Vendedor nuevVendedor2= vendedorController.crearNuevoVendedor("Vendedor Prueba2", "Juan de Garay 123", -34.603722, -58.381592);

            // Actualizar el vendedor
            vendedorController.modificarVendedor(nuevVendedor.getId(), "Vendedor Prueba", "Calle Actualizada 456", -34.603722, -58.381592);
            System.out.println("Vendedor actualizado: Vendedor Prueba, nueva dirección: Calle Actualizada 456");

            // Listar todos los vendedores
            List<Vendedor> vendedores = vendedorController.mostrarListaVendedor();
            System.out.println("Lista de vendedores:");
            for (Vendedor vendedor : vendedores) {
                System.out.println(vendedor.getId() + " - " + vendedor.getNombre());
            }

            // Eliminar el vendedor
            vendedorController.eliminarVendedor(nuevVendedor.getId());
            System.out.println("Vendedor eliminado: Vendedor Prueba");

            // CASO DE PRUEBA 2: ClienteController - CRUD
            System.out.println("\n--- CASO DE PRUEBA 2: ClienteController ---");

            // Crear un nuevo cliente
            Cliente nuevoCliente= clienteController.crearNuevoCliente("20-12345678-9", "clienteprueba@gmail.com", "Avenida Siempre Viva", -34.603722, -58.381592, "Cliente Prueba");
            System.out.println("Cliente creado: Cliente Prueba");

            Cliente nuevoCliente2= clienteController.crearNuevoCliente("20-12345678-9", "clienteprueba@gmail.com", "Te Licuo Como un Topo", -34.603722, -58.381592, "Cliente Prueba");
            System.out.println("Cliente creado: Cliente Prueba");

            // Actualizar el cliente
            clienteController.modificarCliente(nuevoCliente.getId(), "111", "clienteprueba@gmail.com", "Avenida Actualizada", -34.603722, -58.381592,"Cliente Prueba");
            System.out.println("Cliente actualizado: Cliente Prueba, nueva dirección: Avenida Actualizada");

            // Listar todos los clientes
            List<Cliente> clientes = clienteController.mostrarListaCliente();
            System.out.println("Lista de clientes:");
            for (Cliente cliente : clientes) {
                System.out.println(cliente.getId() + " - " + cliente.getNombre());
            }

            // Eliminar el cliente
            clienteController.eliminarCliente(nuevoCliente.getId());
            System.out.println("Cliente eliminado: Cliente Prueba");













            // CASO DE PRUEBA 3: ItemsMenuController - CRUD
            System.out.println("\n--- CASO DE PRUEBA 3: ItemsMenuController ---");

            // Crear una nueva categoría
            Categoria nuevaCate = categoriaController.crearNuevaCategoria("Bebidas", "bebida");

            // Crear un nuevo item de menú (bebida)
           ItemMenu nuevoItemMenu =itemsMenuController.crearNuevoItemMenu("Bebida Prueba", "Descripción Bebida Prueba", 100.0, 0.33, "bebida", nuevaCate.getId(), 5.0, 0.33, 0, false, false, 0.0);
            System.out.println("Item de menú creado: Bebida Prueba"+ nuevoItemMenu.getId());
            ItemMenu nuevoItemMenu2 =itemsMenuController.crearNuevoItemMenu("Bebida Prueba2", "Descripción Bebida Prueba", 100.0, 0.33, "bebida", nuevaCate.getId(), 5.0, 0.33, 0, false, false, 0.0);
            System.out.println("Item de menú creado: Bebida Prueba");

            // Actualizar el item de menú
            itemsMenuController.modificarItemMenu(nuevoItemMenu.getId(), "Bebida Prueba", "Descripción actualizada de la bebida", 120.0, 0.33, "bebida", 1, 5.0, 0.33, 0, false, false, 0.0);
            System.out.println("Item de menú actualizado: Bebida Prueba, nueva descripción: Descripción actualizada de la bebida");

            // Listar todos los items de menú
            List<ItemMenu> itemsMenu = itemsMenuController.mostrarListaItemsMenu();
            System.out.println("Lista de items de menú:");
            for (ItemMenu item : itemsMenu) {
                System.out.println(item.getId() + " - " + item.getNombre() + " (" + item.getCategoria().getDescripcion() + ")");
            }

            // Eliminar el item de menú
            itemsMenuController.eliminarItemMenu(nuevoItemMenu.getId());
            System.out.println("Item de menú eliminado: Bebida Prueba");




            // CASO DE PRUEBA 4: PedidoController - Crear Pedido
            System.out.println("\n--- CASO DE PRUEBA 4: PedidoController ---");

            // Crear un nuevo pedido con detalles
            Cliente clienteParaPedido = clienteController.buscarCliente(nuevoCliente2.getId());
            Vendedor vendedorParaPedido = vendedorController.buscarVendedor(nuevVendedor2.getId());
            List<ItemMenu> itemsPedido = List.of(itemsMenuController.buscarItemMenu(nuevoItemMenu2.getId()));

            List<DetallePedido> detallesPedido = new ArrayList<>();
            for (ItemMenu item : itemsPedido) {
                detallesPedido.add(new DetallePedido(0, item, 2, item.getPrecio(), null));
            }

            pedidoController.crearNuevoPedido(clienteParaPedido, vendedorParaPedido, detallesPedido, Estado.PENDIENTE);
            System.out.println("Pedido creado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
