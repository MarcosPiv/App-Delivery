package com.mycompany.tpintegrador;

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
            String in = JOptionPane.showInputDialog("Ingrese 1 para buscar por Nombre y 2 para buscar por Id: ");
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
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
                    
            }
        }
    }

}
