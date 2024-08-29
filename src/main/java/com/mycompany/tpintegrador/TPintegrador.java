package com.mycompany.tpintegrador;

import com.mycompany.tpintegrador.logica.Cliente;
import com.mycompany.tpintegrador.logica.Coordenada;
import com.mycompany.tpintegrador.logica.Vendedor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class TPintegrador {

    public static void main(String[] args) {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        Vendedor vendedor1 = new Vendedor(1, "Churrassick Park", "Falsa, 123", new Coordenada(-34.603722, -58.381592));
        Vendedor vendedor2 = new Vendedor(2, "Aston Birra", "Alvear, 123", new Coordenada(-14.603722, -28.381592));
        Vendedor vendedor3 = new Vendedor(3, "Lo De Tito", "Hirigoyen, 123", new Coordenada(-44.603722, -18.381592));

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);
        vendedores.add(vendedor3);
        
        boolean bandera = true;
        while (bandera) {
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
                        JOptionPane.showMessageDialog(null, "Vendedor encontrado por ID: " + vendedorPorId.getNombre());
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
        }
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente(1, "20-12345678-9","luchomid@gmail.com", "Mirage 123", new Coordenada(-34.603722, -58.381592),"Luciano");
        Cliente cliente2 = new Cliente(2, "20-44289973-9","meduB@gmail.com","Berettas 222", new Coordenada(-34.603722, -90.381592),"Roberto");
        Cliente cliente3 = new Cliente(3, "20-12138478-9","dracusbot@gmail.com","Dust 222", new Coordenada(-34.603722, -58.381592),"Mario");

        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        bandera = true;
        while (bandera) {
        String in = JOptionPane.showInputDialog("Ingrese para el CLiente:\n1 para buscar por Nombre.\n2 para buscar por Id.\n3 para eliminar por Id.\n4 para eliminar por Nombre: ");
            if(in == null){
                break;
            }
            int selector = Integer.parseInt(in);
            switch (selector) {
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
        
            
        

    }

}
