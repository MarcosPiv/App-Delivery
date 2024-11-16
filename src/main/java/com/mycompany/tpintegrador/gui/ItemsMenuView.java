package com.mycompany.tpintegrador.gui;
import com.mycompany.tpintegrador.config.DatabaseConnection;
import com.mycompany.tpintegrador.logica.Service.Impl.ItemMenuService;
import com.mycompany.tpintegrador.logica.controllers.ItemsMenuController;
import com.mycompany.tpintegrador.logica.models.Bebida;
import com.mycompany.tpintegrador.logica.models.Comida;
import com.mycompany.tpintegrador.logica.models.ItemMenu;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author mpivi
 */
public class ItemsMenuView extends javax.swing.JFrame {
    private ItemsMenuController itemsMenuController;
    /**
     * Creates new form ItemMenuVIew
     */
    public ItemsMenuView() {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            Connection connection = DatabaseConnection.getConnection();
            ItemMenuService itemMenuService = new ItemMenuService();
            itemsMenuController = new ItemsMenuController(itemMenuService);

            customTableSettings();
            cargarItemsMenu();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Metodo para asignar Renderers y Editors
    private void customTableSettings() {
        // Columna de "Editar" (Columna 13)
        jTable1.getColumnModel().getColumn(13).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(13).setCellEditor(new ButtonEditor(new JCheckBox(), jTable1));

        // Columna de "Eliminar" (Columna 14)
        jTable1.getColumnModel().getColumn(14).setCellRenderer(new ButtonRenderer());
        jTable1.getColumnModel().getColumn(14).setCellEditor(new ButtonEditor(new JCheckBox(), jTable1));
    }

    private void cargarItemsMenu() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpiar la tabla antes de cargar datos

        List<ItemMenu> items = itemsMenuController.mostrarListaItemsMenu();
        for (ItemMenu item : items) {
            String tipo = (item instanceof Bebida) ? "Bebida" : "Comida";
            Object[] fila = new Object[]{
                    item.getId(),
                    item.getNombre(),
                    item.getDescripcion(),
                    item.getPrecio(),
                    item.getPeso(),
                    item.getCategoria().getId(),
                    tipo,
                    (item instanceof Bebida) ? ((Bebida) item).getGraduacionAlcoholica() : null,
                    (item instanceof Bebida) ? ((Bebida) item).getTamanio() : null,
                    (item instanceof Comida) ? ((Comida) item).getCalorias() : null,
                    (item instanceof Comida) ? ((Comida) item).isAptoVegano() : null,
                    (item instanceof Comida) ? ((Comida) item).isAptoCeliaco() : null,
                    (item instanceof Comida) ? ((Comida) item).getPesoSinEnvase() : null,
                    "Editar",
                    "Eliminar"
            };
            model.addRow(fila);
        }
    }

    // Clase ButtonRenderer para mostrar los botones
    public class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Clase ButtonEditor para manejar la acción de los botones
    public class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private String label;
        private boolean isPushed;
        private JTable table;

        public ButtonEditor(JCheckBox checkBox, JTable table) {
            super(checkBox);
            this.table = table;

            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                    int row = table.getSelectedRow();
                    if (label.equals("Eliminar")) {
                        eliminarItemMenu(row);
                    } else if (label.equals("Editar")) {
                        mostrarVentanaModificar(row); //Mostrar la ventana de modificación
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }

        private void eliminarItemMenu(int row) {
            int id = (int) jTable1.getValueAt(row, 0);
            int confirm = JOptionPane.showConfirmDialog(button, "¿Está seguro de eliminar este Item?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                itemsMenuController.eliminarItemMenu(id);
                cargarItemsMenu();;
                JOptionPane.showMessageDialog(button, "Item eliminado.");
            } else {
                System.out.println("Eliminación cancelada.");
            }
        }

        private void mostrarVentanaModificar(int row) {
            JFrame modificarFrame = new JFrame("Modificar Item Menu");
            modificarFrame.setSize(500, 700);
            modificarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            modificarFrame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            int id = (int) jTable1.getValueAt(row, 0);
            String nombreActual = (String) jTable1.getValueAt(row, 1);
            String descripcionActual = (String) jTable1.getValueAt(row, 2);
            double precioActual = (double) jTable1.getValueAt(row, 3);
            double pesoActual = (double) jTable1.getValueAt(row, 4);
            int categoriaId = (int) jTable1.getValueAt(row, 5);
            String tipo = (String) jTable1.getValueAt(row, 6);
            double graduacionActual = jTable1.getValueAt(row, 7) != null ? (double) jTable1.getValueAt(row, 7) : 0.0;
            double tamanioActual = jTable1.getValueAt(row, 8) != null ? (double) jTable1.getValueAt(row, 8) : 0.0;
            int caloriasActual = jTable1.getValueAt(row, 9) != null ? (int) jTable1.getValueAt(row, 9) : 0;
            boolean aptoVeganoActual = jTable1.getValueAt(row, 10) != null && (boolean) jTable1.getValueAt(row, 10);
            boolean aptoCeliacoActual = jTable1.getValueAt(row, 11) != null && (boolean) jTable1.getValueAt(row, 11);
            double pesoSinEnvaseActual = jTable1.getValueAt(row, 12) != null ? (double) jTable1.getValueAt(row, 12) : 0.0;

            JTextField txtNombre = new JTextField(nombreActual);
            JTextField txtDescripcion = new JTextField(descripcionActual);
            JTextField txtPrecio = new JTextField(String.valueOf(precioActual));
            JTextField txtPeso = new JTextField(String.valueOf(pesoActual));
            JTextField txtCategoriaId = new JTextField(String.valueOf(categoriaId));
            JTextField txtTipo = new JTextField(tipo);
            JTextField txtGraduacion = new JTextField(String.valueOf(graduacionActual));
            JTextField txtTamanio = new JTextField(String.valueOf(tamanioActual));
            JTextField txtCalorias = new JTextField(String.valueOf(caloriasActual));
            JCheckBox chkAptoVegano = new JCheckBox("", aptoVeganoActual);
            JCheckBox chkAptoCeliaco = new JCheckBox("", aptoCeliacoActual);
            JTextField txtPesoSinEnvase = new JTextField(String.valueOf(pesoSinEnvaseActual));

            panel.add(new JLabel("Nombre:"));
            panel.add(txtNombre);
            panel.add(new JLabel("Descripcion:"));
            panel.add(txtDescripcion);
            panel.add(new JLabel("Precio:"));
            panel.add(txtPrecio);
            panel.add(new JLabel("Peso:"));
            panel.add(txtPeso);
            panel.add(new JLabel("Categoria ID:"));
            panel.add(txtCategoriaId);
            panel.add(new JLabel("Tipo:"));
            panel.add(txtTipo);
            panel.add(new JLabel("Graduacion Alcoholica:"));
            panel.add(txtGraduacion);
            panel.add(new JLabel("Tamaño:"));
            panel.add(txtTamanio);
            panel.add(new JLabel("Calorias:"));
            panel.add(txtCalorias);
            panel.add(new JLabel("Apto Vegano:"));
            panel.add(chkAptoVegano);
            panel.add(new JLabel("Apto Celiaco:"));
            panel.add(chkAptoCeliaco);
            panel.add(new JLabel("Peso Sin Envase:"));
            panel.add(txtPesoSinEnvase);

            JButton btnGuardar = new JButton("Guardar Cambios");
            btnGuardar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String nuevoNombre = txtNombre.getText();
                        String nuevaDescripcion = txtDescripcion.getText();
                        double nuevoPrecio = Double.parseDouble(txtPrecio.getText());
                        double nuevoPeso = Double.parseDouble(txtPeso.getText());
                        int nuevaCategoriaId = Integer.parseInt(txtCategoriaId.getText());
                        String nuevoTipo = txtTipo.getText();
                        double nuevaGraduacion = Double.parseDouble(txtGraduacion.getText());
                        double nuevoTamanio = Double.parseDouble(txtTamanio.getText());
                        int nuevasCalorias = Integer.parseInt(txtCalorias.getText());
                        boolean nuevoAptoVegano = chkAptoVegano.isSelected();
                        boolean nuevoAptoCeliaco = chkAptoCeliaco.isSelected();
                        double nuevoPesoSinEnvase = Double.parseDouble(txtPesoSinEnvase.getText());

                        itemsMenuController.modificarItemMenu(id, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevoPeso,
                                nuevoTipo, nuevaCategoriaId, nuevaGraduacion, nuevoTamanio, nuevasCalorias,
                                nuevoAptoVegano, nuevoAptoCeliaco, nuevoPesoSinEnvase);

                        cargarItemsMenu();
                        modificarFrame.dispose();
                        JOptionPane.showMessageDialog(null, "Item actualizado con éxito.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(modificarFrame, "Los campos deben ser numéricos donde corresponda.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            panel.add(btnGuardar);
            modificarFrame.add(panel);
            modificarFrame.setVisible(true);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vendedores");
        setPreferredSize(new java.awt.Dimension(1700, 700));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setText("Vendedores");
        jButton3.setToolTipText("");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Clientes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton4.setText("ItemsMenus");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Pedidos");
        jButton1.setPreferredSize(new java.awt.Dimension(94, 33));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setPreferredSize(new java.awt.Dimension(1048, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Buscador:   ");
        jPanel3.add(jLabel1);

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setPreferredSize(new java.awt.Dimension(250, 30));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1);

        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Buscar");
        jButton5.setPreferredSize(new java.awt.Dimension(100, 35));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel2.setPreferredSize(new java.awt.Dimension(1048, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Agregar Vendedor");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion:");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Peso: ");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("CategoriaID: ");

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Agregar");
        jButton6.setPreferredSize(new java.awt.Dimension(100, 35));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jTextField7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Precio:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tipo: ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Calorias");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tamaño:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Graduacion:");

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextField10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTextField11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Peso Sin Envase: ");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("AptoCeliaco: ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Apto Vegano: ");

        jTextField14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Comida", "Bebida" }));

        jCheckBox2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1)
                                    .addComponent(jCheckBox2))
                                .addGap(69, 69, 69)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))))
                .addContainerGap(252, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(29, 29, 29)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel12)
                                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7)
                                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel11)
                                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14))
                                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel4.setPreferredSize(new java.awt.Dimension(885, 500));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Descripcion", "Precio", "Peso", "CategoriaID", "Tipo", "Graduacion", "Tamaño", "Calorias", "Apto Vegano", "Apto Celiaco", "Peso Sin Envase", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Byte.class, java.lang.Byte.class, java.lang.Double.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
            jTable1.getColumnModel().getColumn(9).setResizable(false);
            jTable1.getColumnModel().getColumn(10).setResizable(false);
            jTable1.getColumnModel().getColumn(11).setResizable(false);
            jTable1.getColumnModel().getColumn(12).setResizable(false);
            jTable1.getColumnModel().getColumn(14).setResizable(false);
        }

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ClienteView clientesView = new ClienteView();
        clientesView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        try {
            //datos comunes
            String nombre = jTextField4.getText();
            String descripcion = jTextField3.getText();
            double precio = Double.parseDouble(jTextField7.getText());
            double peso = Double.parseDouble(jTextField5.getText());
            int categoriaId = Integer.parseInt(jTextField6.getText());
            String tipo = jComboBox1.getSelectedItem().toString();

            // Validación según el tipo de ItemMenu
            if ("Bebida".equalsIgnoreCase(tipo)) {
                double graduacionAlcoholica;
                double tamanio;

                try {
                    graduacionAlcoholica = Double.parseDouble(jTextField9.getText());
                    tamanio = Double.parseDouble(jTextField10.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Graduación y Tamaño deben ser numéricos para una bebida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear el ítem como Bebida
                itemsMenuController.crearNuevoItemMenu(nombre, descripcion, precio, peso, tipo, categoriaId, graduacionAlcoholica, tamanio, 0, false, false, 0.0);

            } else if ("Comida".equalsIgnoreCase(tipo)) {
                int calorias;
                boolean aptoVegano = jCheckBox1.isSelected();
                boolean aptoCeliaco = jCheckBox2.isSelected();
                double pesoSinEnvase;

                try {
                    calorias = Integer.parseInt(jTextField11.getText());
                    pesoSinEnvase = Double.parseDouble(jTextField14.getText());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Calorías y Peso sin Envase deben ser numéricos para una comida.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Crear el ítem como Comida
                itemsMenuController.crearNuevoItemMenu(nombre, descripcion, precio, peso, tipo, categoriaId, 0.0, 0.0, calorias, aptoVegano, aptoCeliaco, pesoSinEnvase);
            } else {
                JOptionPane.showMessageDialog(this, "Tipo de ítem no válido. Seleccione 'Bebida' o 'Comida'.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            jTextField3.setText("");
            jTextField4.setText("");
            jTextField7.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jComboBox1.setSelectedIndex(0);
            jTextField14.setText("");
            jTextField9.setText("");
            jTextField10.setText("");
            jCheckBox1.setSelected(false);
            jCheckBox2.setSelected(false);
            jTextField11.setText("");

            //Recargar la tabla para reflejar el nuevo ítem
            cargarItemsMenu();
            JOptionPane.showMessageDialog(this, "Item de menú agregado con éxito.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Los campos comunes deben ser numéricos donde corresponda.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String textoBuscado = jTextField1.getText();

        if (textoBuscado.trim().isEmpty()) {
            cargarItemsMenu();
        } else {
            try {
                int itemId = Integer.parseInt(textoBuscado);
                ItemMenu itemMenu = itemsMenuController.buscarItemMenu(itemId);

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);

                if (itemMenu == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró un ítem con el ID: " + itemId);
                } else {
                    Object[] fila = {
                            itemMenu.getId(),
                            itemMenu.getNombre(),
                            itemMenu.getDescripcion(),
                            itemMenu.getPrecio(),
                            itemMenu.getPeso(),
                            itemMenu.getCategoria().getId(),
                            itemMenu.getCategoria().getTipoItem(),
                            (itemMenu instanceof Bebida) ? ((Bebida) itemMenu).getGraduacionAlcoholica() : null,
                            (itemMenu instanceof Bebida) ? ((Bebida) itemMenu).getTamanio() : null,
                            (itemMenu instanceof Comida) ? ((Comida) itemMenu).getCalorias() : null,
                            (itemMenu instanceof Comida) ? ((Comida) itemMenu).isAptoVegano() : null,
                            (itemMenu instanceof Comida) ? ((Comida) itemMenu).isAptoCeliaco() : null,
                            (itemMenu instanceof Comida) ? ((Comida) itemMenu).getPesoSinEnvase() : null,
                            "Editar",
                            "Eliminar"
                    };
                    model.addRow(fila);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID numérico.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        VendedorView vendedorView = new VendedorView();
        vendedorView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        PedidosView pedidosView = new PedidosView();
        pedidosView.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItemsMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemsMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemsMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemsMenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ItemsMenuView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
