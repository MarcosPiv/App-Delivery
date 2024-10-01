package com.mycompany.tpintegrador;

import com.mycompany.tpintegrador.logica.*;
import com.mycompany.tpintegrador.logica.businessExceptions.ItemNoEncontradoException;
import com.mycompany.tpintegrador.respository.impl.ItemPedidoMemory;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TPintegrador2 {

    public static void main(String[] args) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        //Crear vendedores
        Vendedor vendedor1 = new Vendedor(1, "Churrassick Park", "Falsa, 123", new Coordenada(-34.603722, -58.381592));
        Vendedor vendedor2 = new Vendedor(2, "Aston Birra", "Alvear, 123", new Coordenada(-14.603722, -28.381592));
        Vendedor vendedor3 = new Vendedor(3, "Lo De Tito", "Hirigoyen, 123", new Coordenada(-44.603722, -18.381592));
        
        Comida plato1 = new Comida(1,10, false, true, 20,"Plato1","desc1",10.3,new Categoria(1, "Cat1","Comida"),25.68);
        Comida plato2 = new Comida(2,15, true, false, 25,"Plato2","desc2",20.3,new Categoria(2, "Cat2","Comida"),30.68);
        Comida plato3 = new Comida(3,20, false, true, 30,"Plato3","desc3",30.3,new Categoria(3, "Cat3","Comida"),35.68);
        Comida plato4 = new Comida(4,25, true, false, 35,"Plato4","desc4",40.3,new Categoria(4, "Cat4","Comida"),40.68);
        
        vendedor1.addItemMenu(plato1);
        vendedor1.addItemMenu(plato2);
        vendedor1.addItemMenu(plato3);
        vendedor1.addItemMenu(plato4);

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        vendedores.add(vendedor3);
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(1, "20-12345678-9","luchomid@gmail.com", "Mirage 123", new Coordenada(-34.603722, -58.381592),"Luciano");
        Cliente cliente2 = new Cliente(2, "20-44289973-9","meduB@gmail.com","Berettas 222", new Coordenada(-34.603722, -90.381592),"Roberto");
        Cliente cliente3 = new Cliente(3, "20-12138478-9","dracusbot@gmail.com","Dust 222", new Coordenada(-34.603722, -58.381592),"Mario");
            
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);
        
        ArrayList<Pedido> listaDePedidos = new ArrayList<>();

        Pedido pedido1 = new Pedido(1, cliente1, vendedor1, 1000, new ArrayList<>(), Estado.PENDIENTE);
        Pedido pedido2 = new Pedido(2, cliente2, vendedor2, 2000, new ArrayList<>(), Estado.PENDIENTE);
        Pedido pedido3 = new Pedido(3, cliente3, vendedor3, 3000, new ArrayList<>(), Estado.PENDIENTE);

        listaDePedidos.add(pedido1);
        listaDePedidos.add(pedido2);
        listaDePedidos.add(pedido3);
        
        DetallePedido detallePedido1 = new DetallePedido(1, new Bebida(50,350, 0, null, null, 0, null, 0), 2, 700,pedido1);
        DetallePedido detallePedido2 = new DetallePedido(2, new Bebida(51,350, 0, null, null, 0, null, 0), 2, 100,pedido1);
        DetallePedido detallePedido3 = new DetallePedido(3, new Bebida(52,350, 0, null, null, 0, null, 0), 2, 200,pedido1);
        DetallePedido detallePedido4 = new DetallePedido(4, new Comida(53,0, true, true,500, "a", null, 0, null, 0), 1, 300,pedido1);
        DetallePedido detallePedido5 = new DetallePedido(5, new Comida(511,0, true, true,700, "b", null, 0, null, 0), 1, 400,pedido1);
        DetallePedido detallePedido6 = new DetallePedido(6, new Comida(1002,0, false, false,800, "c", null, 0, null, 0), 1, 5000,pedido1);
        DetallePedido detallePedido7 = new DetallePedido(7, new Comida(1003,0, false, false,1100, "d", null, 0, null, 0), 1, 600,pedido1);
        DetallePedido detallePedido8 = new DetallePedido(8, new Comida(1004,0, true, false,1200, "e", null, 0, null, 0), 1, 700,pedido1);

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
                        if(in == null){
                            break;
                        }
                        StringBuilder mensaje = new StringBuilder();
                        
                        ArrayList<ItemMenu> itemsDeVendedorEncontrado = Vendedor.buscarVendedorPorNombre(vendedores,in).getItemsMenu();
                        mensaje.append("Id  Plato\n");

                        for (int i = 0; i < itemsDeVendedorEncontrado.size(); i++) {
                            mensaje.append(itemsDeVendedorEncontrado.get(i).getId()+"  "+itemsDeVendedorEncontrado.get(i).getNombre()+"\n");
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

                            pedidoGenerado = vendedor1.generarPedido(idCantidad,vendedor1,cliente1);
                            /*System.out.println(pedidoGenerado.getId());
                            System.out.println(pedidoGenerado.getEstado());
                            System.out.println(pedidoGenerado.getPrecioTotal());
                            System.out.println(pedidoGenerado.getCliente().getNombre());*/
                            itemPedidoMemory.addPedido(pedidoGenerado);
                        
                        
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
                                                            "\nEl prcio total es:"+ pedidoGenerado.getPrecioTotal()+
                                                            "\nEl prcio total pagando con Mercado Pago seria: "+ pedidoGenerado.getTipoDePago().getImporte());

                            break;

                            case 2:
                                String cuit = JOptionPane.showInputDialog("Ingrese su cuit");
                                String cbu = JOptionPane.showInputDialog("Ingrese su cbu");
                                int cbuInt = Integer.parseInt(cbu);
                                pedidoGenerado.pagarTransferencia(cbuInt, cuit, pedidoGenerado.getPrecioTotal());
                                JOptionPane.showMessageDialog(null,"El cuit que ingreso es "+cuit+
                                                            "\nEl cbu que ingreso es "+cbuInt+
                                                            "\nEl prcio total es:"+ pedidoGenerado.getPrecioTotal()+
                                                            "\nEl prcio total pagando con Mercado Pago seria: "+ pedidoGenerado.getTipoDePago().getImporte());

                            break;
                        }
                                    
                        
                        bandera = false;
                    
                    }    

                    default:
                    break;
            }
            
        

        
        }
    }  
}/*
        StringBuilder mensaje = new StringBuilder();
        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.filtrarPedidosPorEstado("pendiente");
            mensaje.append("Estados de Pedidos: [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getEstado());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.filtrarPorPrecioMinimo(2000);
            mensaje.append("Precio Total (Min): [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getPrecioTotal());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.filtrarPorPrecioMaximo(2000);
            mensaje.append("Precio Total (Max): [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getPrecioTotal());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.ordenarPorPrecio();
            mensaje.append("Precio Total (Ordenado): [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getPrecioTotal());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.ordenarPorNombre();
            mensaje.append("Nombre (Ordenado): [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getRestaurante().getNombre());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try{
            List<Pedido> pedidosFiltrados = itemPedidoMemory.ordenarPorId();
            mensaje.append("Id (Ordenado): [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getId());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.buscarItemPedidoPorRangoPrecio(1000, 2000);
            mensaje.append("Rango de Precio: [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getPrecioTotal());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }

        try {
            List<Pedido> pedidosFiltrados = itemPedidoMemory.buscarItemPedidoPorRestaurante(vendedor2.getNombre());
            mensaje.append("Restaurante: [");
            for (int i = 0; i < pedidosFiltrados.size(); i++) {
                mensaje.append(pedidosFiltrados.get(i).getRestaurante().getNombre());
                if (i < pedidosFiltrados.size() - 1) {
                    mensaje.append(", ");
                }
            }
            mensaje.append("]\n");
        } catch (ItemNoEncontradoException e) {
            mensaje.append(e.getMessage()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());*/
