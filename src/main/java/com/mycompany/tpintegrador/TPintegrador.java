package com.mycompany.tpintegrador;

import com.mycompany.tpintegrador.gui.VendedorView;

public class TPintegrador {

    public static void main(String[] args) {

        //Ejecutar la interfaz gráfica
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VendedorView vendedorView = new VendedorView(); // Instanciar la vista
                vendedorView.setVisible(true);
                vendedorView.setLocationRelativeTo(null);
            }
        });
    }
}
