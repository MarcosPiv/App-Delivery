package com.mycompany.tpintegrador;

import com.mycompany.tpintegrador.accesodatos.impl.ItemPedidoMemory;
import com.mycompany.tpintegrador.logica.models.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TPintegrador {

    public static void main(String[] args) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        //Crear vendedores
        Vendedor vendedor1 = new Vendedor(1, "Churrassick Park", "Falsa, 123", new Coordenada(-34.603722, -58.381592));
        Vendedor vendedor2 = new Vendedor(2, "Aston Birra", "Alvear, 123", new Coordenada(-14.603722, -28.381592));
        Vendedor vendedor3 = new Vendedor(3, "Lo De Tito", "Hirigoyen, 123", new Coordenada(-44.603722, -18.381592));
        
        Comida plato1 = new Comida(1,10, false, true, 20,"Churro chocolate","desc1",1000, new Categoria(1, "Cat1","Comida"),25.68);
        Comida plato2 = new Comida(2,15, true, false, 25,"Churro ddl","desc2",1200, new Categoria(2, "Cat2","Comida"),30.68);
        Comida plato3 = new Comida(3,20, false, true, 30,"Medialuna","desc3",300, new Categoria(3, "Cat3","Comida"),35.68);
        Comida plato4 = new Comida(4,25, true, false, 35,"Alafajor","desc4",400, new Categoria(4, "Cat4","Comida"),40.68);
        Bebida bebida1 = new Bebida(5, 50, 0, "Cerveza negra", "desc1", 1700, new Categoria(5, "Cat5","Bebida"), 25.68);
        Bebida bebida2 = new Bebida(6, 50, 0, "Cerveza rubia", "desc2", 1200, new Categoria(6, "Cat6","Bebida"), 30.68);
        Bebida bebida3 = new Bebida(7, 50, 0, "Cerveza IPA", "desc3", 2000, new Categoria(7, "Cat7","Bebida"), 35.68);
        Bebida bebida4 = new Bebida(8, 50, 0, "Tequila", "desc4", 300, new Categoria(8, "Cat8","Bebida"), 40.68);

        vendedor1.addItemMenu(plato1);
        vendedor1.addItemMenu(plato2);
        vendedor1.addItemMenu(plato3);
        vendedor1.addItemMenu(plato4);
        vendedor2.addItemMenu(bebida1);
        vendedor2.addItemMenu(bebida2);
        vendedor2.addItemMenu(bebida3);
        vendedor3.addItemMenu(bebida4);

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        vendedores.add(vendedor3);

        //Crear de clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(1, "20-12345678-9","luchomid@gmail.com", "Mirage 123", new Coordenada(-34.603722, -58.381592),"Luciano");
        Cliente cliente2 = new Cliente(2, "20-44289973-9","meduB@gmail.com","Berettas 222", new Coordenada(-34.603722, -90.381592),"Roberto");
        Cliente cliente3 = new Cliente(3, "20-12138478-9","dracusbot@gmail.com","Dust 222", new Coordenada(-34.603722, -58.381592),"Mario");
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        //pedidos ya generados
        ArrayList<Pedido> listaDePedidos = new ArrayList<>();
        Pedido pedido1 = new Pedido(1, cliente1, vendedor1, 1000, new ArrayList<>(), Estado.PENDIENTE);
        Pedido pedido2 = new Pedido(2, cliente2, vendedor2, 2000, new ArrayList<>(), Estado.PENDIENTE);
        Pedido pedido3 = new Pedido(3, cliente3, vendedor3, 3000, new ArrayList<>(), Estado.PENDIENTE);

        listaDePedidos.add(pedido1);
        listaDePedidos.add(pedido2);
        listaDePedidos.add(pedido3);

        DetallePedido detallePedido1 = new DetallePedido(1, bebida1, 2, 700, pedido1);
        DetallePedido detallePedido2 = new DetallePedido(2, bebida2, 2, 100, pedido1);
        DetallePedido detallePedido3 = new DetallePedido(3, bebida3, 2, 200, pedido1);
        DetallePedido detallePedido4 = new DetallePedido(4, plato1, 1, 300, pedido1);
        DetallePedido detallePedido5 = new DetallePedido(5, plato2, 1, 400, pedido2);
        DetallePedido detallePedido6 = new DetallePedido(6, plato3, 1, 5000, pedido2);
        DetallePedido detallePedido7 = new DetallePedido(7, plato4, 1, 600, pedido2);
        DetallePedido detallePedido8 = new DetallePedido(8, bebida4, 1, 700, pedido3);

        pedido1.setDetallesPedido(new ArrayList<>(List.of(detallePedido1, detallePedido2, detallePedido3, detallePedido4)));
        pedido2.setDetallesPedido(new ArrayList<>(List.of(detallePedido5, detallePedido6, detallePedido7)));
        pedido3.setDetallesPedido(new ArrayList<>(List.of(detallePedido8)));

        ItemPedidoMemory itemPedidoMemory = new ItemPedidoMemory(listaDePedidos);    

        boolean bandera = true;
        while (bandera) {
            String opcion = JOptionPane.showInputDialog("Elija una opcion: \n1 Vendedor \n2 Clientes \n3 Carta por Vendedor \n4 Generar Pedido");
            if(opcion == null){
                break;
            }
            int selector2 = Integer.parseInt(opcion);
            switch (selector2) {
                case 1:
                    String in = JOptionPane.showInputDialog("Ingrese para el Vendedor:\n1 para buscar por Nombre.\n2 para buscar por Id.\n3 para eliminar por Id.\n4 para eliminar por Nombre: ");
                    if(in == null){
                        break;
                    }
                    int selector = Integer.parseInt(in);
                    switch (selector) {
                        case 1:
                            String vend = JOptionPane.showInputDialog("Ingrese el vendedor: ");
                            Vendedor vendedorPorNombre = Vendedor.buscarVendedorPorNombre(vendedores, vend);
                            if (vendedorPorNombre != null) {
                                JOptionPane.showMessageDialog(null, "Vendedor encontrado por nombre: " + vendedorPorNombre.getNombre());
                            } else {
                                JOptionPane.showMessageDialog(null, "Vendedor no encontrado por Nombre.");
                            }
                            bandera = false;
                        break;
                        case 2:
                            String numeroIngresado = JOptionPane.showInputDialog("Ingrese el id: ");
                            int numero = Integer.parseInt(numeroIngresado);
                            Vendedor vendedorPorId = Vendedor.buscarVendedorPorId(vendedores, numero);
                            if (vendedorPorId != null) {
                                JOptionPane.showMessageDialog(null, "Vendedor encontrado por ID: " + vendedorPorId.getNombre()+vendedorPorId.getItemBebida().toString());
                            } else {
                                JOptionPane.showMessageDialog(null, "Vendedor no encontrado por ID.");
                            }
                            bandera = false;
                        break;
                        case 3:
                            String idIngresado = JOptionPane.showInputDialog("Ingrese el id: ");
                            if(idIngresado == null){
                                break;
                            }
                            int id = Integer.parseInt(idIngresado);
                            vendedorPorId = Vendedor.buscarVendedorPorId(vendedores, id);
                            if(vendedorPorId != null){
                                vendedores.remove(vendedorPorId);
                                JOptionPane.showMessageDialog(null, "Vendedor eliminado por ID.");
                            }else {
                                JOptionPane.showMessageDialog(null, "Vendedor no encontrado por ID.");
                            }
                            bandera=false;
                        break;
                        case 4:
                            String nombreIngresado = JOptionPane.showInputDialog("Ingrese el nombre: ");
                            if(nombreIngresado == null){
                                break;
                            }
                            vendedorPorNombre = Vendedor.buscarVendedorPorNombre(vendedores, nombreIngresado);
                            if(vendedorPorNombre != null){
                                vendedores.remove(vendedorPorNombre);
                                JOptionPane.showMessageDialog(null, "Vendedor eliminado por Nombre.");
                            }else {
                                JOptionPane.showMessageDialog(null, "Vendedor no encontrado por Nombre.");
                            }
                            bandera=false;
                        break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida.");
                    }
                    break;
                case 2:
                    bandera = true;
                    while (bandera) {
                        in = JOptionPane.showInputDialog("Ingrese para el Ciiente:\n1 para buscar por Nombre.\n2 para buscar por Id.\n3 para eliminar por Id.\n4 para eliminar por Nombre: ");
                        if(in == null){
                            break;
                        }
                        int selector3 = Integer.parseInt(in);
                        switch (selector3) {
                            case 1:
                                String cliente = JOptionPane.showInputDialog("Ingrese el Cliente: ");
                                Cliente clientePorNombre = Cliente.buscarClientePorNombre(clientes, cliente);
                                if (clientePorNombre != null) {
                                    JOptionPane.showMessageDialog(null, "Cliente encontrado por nombre: " + clientePorNombre.getNombre());
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cliente no encontrado por Nombre.");
                                }
                                bandera = false;
                            break;
                            case 2:
                                String numeroIngresado = JOptionPane.showInputDialog("Ingrese el id: ");
                                int numero = Integer.parseInt(numeroIngresado);
                                Cliente clientePorId = Cliente.buscarClientePorId(clientes, numero);
                                if (clientePorId != null) {
                                    JOptionPane.showMessageDialog(null, "cliente encontrado por ID: " +clientePorId.getNombre());
                                } else {
                                    JOptionPane.showMessageDialog(null, "cliente no encontrado por ID.");
                                }
                                bandera = false;
                            break;
                            case 3:
                                String idIngresado = JOptionPane.showInputDialog("Ingrese el id: ");
                                if(idIngresado == null){
                                    break;
                                }
                                int id = Integer.parseInt(idIngresado);
                                clientePorId = Cliente.buscarClientePorId(clientes, id);
                                if(clientePorId != null){
                                    clientes.remove(clientePorId);
                                    JOptionPane.showMessageDialog(null, "Cliente eliminado por ID.");
            
                                }else {
                                    JOptionPane.showMessageDialog(null, "Cliente no encontrado por ID.");
                                }
                                bandera=false;
                            break;
                            case 4:
                                String nombreIngresado = JOptionPane.showInputDialog("Ingrese el nombre: ");
                                if(nombreIngresado == null){
                                    break;
                                }
                                clientePorNombre = Cliente.buscarClientePorNombre(clientes, nombreIngresado);
                                if(clientePorNombre != null){
                                    clientes.remove(clientePorNombre);
                                    JOptionPane.showMessageDialog(null, "Cliente eliminado por Nombre.");
                                }else {
                                    JOptionPane.showMessageDialog(null, "Cliente no encontrado por Nombre.");
                                }
                                bandera=false;
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opción no válida.");
                        }
                    }
                    break;
                case 3:
                    bandera = true;
                    while (bandera) {
                        in = JOptionPane.showInputDialog("Ingrese el nombre del Vendedor para ver sus Items");
                        if(in == null){
                            break;
                        }
                        StringBuilder mensaje = new StringBuilder();
                        ArrayList<ItemMenu> itemsDeVendedorEncontrado = Vendedor.buscarVendedorPorNombre(vendedores,in).getItemsMenu();
                            for (int i = 0; i < itemsDeVendedorEncontrado.size(); i++) {
                                mensaje.append(itemsDeVendedorEncontrado.get(i).getId()+"  "+itemsDeVendedorEncontrado.get(i).getNombre()+"\n");
                            }
                        JOptionPane.showMessageDialog(null, mensaje);
                        bandera = false;
                    }
                    break;
                case 4:
                    bandera = true;
                    while (bandera) {
                        in = JOptionPane.showInputDialog("Ingrese el nombre del Vendedor ");
                        if (in == null) {
                            break;
                        }
                        Vendedor vendedorSeleccionado = Vendedor.buscarVendedorPorNombre(vendedores, in);
                        if (vendedorSeleccionado == null) {
                            JOptionPane.showMessageDialog(null, "Vendedor no encontrado.");
                            continue;
                        }

                        String clienteNombre = JOptionPane.showInputDialog("Ingrese el nombre del Cliente ");
                        if (clienteNombre == null) {
                            break;
                        }
                        Cliente clienteSeleccionado = Cliente.buscarClientePorNombre(clientes, clienteNombre);
                        if (clienteSeleccionado == null) {
                            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                            continue;
                        }

                        StringBuilder mensaje = new StringBuilder();
                        ArrayList<ItemMenu> itemsDeVendedorEncontrado = vendedorSeleccionado.getItemsMenu();
                        mensaje.append("Id  Plato\n");
                        for (int i = 0; i < itemsDeVendedorEncontrado.size(); i++) {
                            mensaje.append(itemsDeVendedorEncontrado.get(i).getId() + "  " + itemsDeVendedorEncontrado.get(i).getNombre() + "\n");
                        }
                        Pedido pedidoGenerado = null;
                        try {
                            String input = JOptionPane.showInputDialog(null, mensaje + "\n" +
                                            "Ingrese los id y cantidad de los platos que desea agregar al pedido\n" +
                                            "Ejemplo: 1 2, 3 4 (donde 1 y 3 son ids, y 2 y 4 son las cantidades)",
                                    "Input", JOptionPane.QUESTION_MESSAGE);
                            String[] inputArray = input.split(",");
                            int[][] idCantidad = new int[inputArray.length][2];
                            for (int i = 0; i < inputArray.length; i++) {
                                String[] idYcantidad = inputArray[i].trim().split(" ");
                                idCantidad[i][0] = Integer.parseInt(idYcantidad[0].trim()); // ID
                                idCantidad[i][1] = Integer.parseInt(idYcantidad[1].trim()); // Cantidad
                            }
                            pedidoGenerado = vendedorSeleccionado.generarPedido(idCantidad, vendedorSeleccionado, clienteSeleccionado);
                            itemPedidoMemory.addPedido(pedidoGenerado);;
                            /*
                            System.out.println(pedidoGenerado.getEstado());
                            System.out.println(pedidoGenerado.getPrecioTotal());
                            System.out.println(pedidoGenerado.getCliente().getNombre());
                           */

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Por favor, ingrese solo números enteros en el formato correcto (id cantidad).", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            JOptionPane.showMessageDialog(null, "Formato incorrecto. Asegúrese de ingresar cada id seguido por una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        String opcionDePago = JOptionPane.showInputDialog("Elija una opcion de Pago: \n1 Mercado Pago \n2 Transferencia");
                        if(opcionDePago == null){
                            break;
                        }
                        int selectorP = Integer.parseInt(opcionDePago);
                        switch (selectorP) {
                            case 1:
                                String alias = JOptionPane.showInputDialog("Ingrese su alias");
                                pedidoGenerado.pagarMercadoPago(alias,pedidoGenerado.getPrecioTotal());
                                JOptionPane.showMessageDialog(null,"El alias que ingreso es "+alias+
                                                            "\nEl precio total es:"+ pedidoGenerado.getPrecioTotal()+
                                                            "\nEl precio total pagando con Mercado Pago seria: "+ pedidoGenerado.getTipoDePago().getImporte());
                            break;
                            case 2:
                                String cuit = JOptionPane.showInputDialog("Ingrese su cuit");
                                String cbu = JOptionPane.showInputDialog("Ingrese su cbu");
                                int cbuInt = Integer.parseInt(cbu);
                                pedidoGenerado.pagarTransferencia(cbuInt, cuit, pedidoGenerado.getPrecioTotal());
                                JOptionPane.showMessageDialog(null,"El cuit que ingreso es "+cuit+
                                                            "\nEl cbu que ingreso es "+cbuInt+
                                                            "\nEl precio total es:"+ pedidoGenerado.getPrecioTotal()+
                                                            "\nEl precio total pagando con Mercado Pago seria: "+ pedidoGenerado.getTipoDePago().getImporte());
                            break;
                        }
                        bandera = false;
                    }
                break;
            }
        }
    }  
}