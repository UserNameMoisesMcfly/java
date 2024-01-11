package Formularios;

import Clases.Cls_Productos;
import static Formularios.Frm_Principal.contenedor;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class Frm_Productos extends javax.swing.JInternalFrame {

    private final Cls_Productos CP;
    TableColumnModel columnModel;
    int num = 0;

    public Frm_Productos() {
        initComponents();
        CP = new Cls_Productos();
        CP.ConsultarProveedor(cbxProveedor);           
        CP.ConsultarCategorias(cbxCategoria);         
        columnModel = jtb_productos.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
    }

    private void listar() {
        jtb_productos.setModel(CP.getDatosProductos());
        columnModel.getColumn(1).setPreferredWidth(600);
    }

    private void iniciar() {
        txt_codigo.setEnabled(false);
        txt_descripcion.setEnabled(false);
        cbxProveedor.setEnabled(false);
        cbxCategoria.setEnabled(false);
        txt_ubicacion.setEnabled(false);
        //txt_estado.setEnabled(false);
        //txt_cuerpo.setEnabled(false);
        //txt_reja.setEnabled(false);
        //txt_tapa.setEnabled(false);
    }

    private void activar() {
        txt_codigo.setEnabled(true);
        txt_descripcion.setEnabled(true);
        cbxProveedor.setEnabled(true);
        cbxCategoria.setEnabled(true);
        txt_ubicacion.setEnabled(true);
        //txt_estado.setEnabled(true);
        //txt_cuerpo.setEnabled(true);
        //txt_reja.setEnabled(true);
        //txt_tapa.setEnabled(true);
        txt_codigo.requestFocus();
    }

    private void limpiar() {
        txt_codigo.setText("");
        txt_descripcion.setText("");
        txt_codigo.requestFocus();
        jtb_productos.clearSelection();
        txt_ubicacion.setText("");
        //txt_estado.setText("");
        //txt_cuerpo.setText("");
        //txt_reja.setText("");
        //txt_tapa.setText("");
    }

    private void guardar() {
    String codigo = txt_codigo.getText();
    String descripcion = txt_descripcion.getText();
    String nomproveedor = (String) cbxProveedor.getSelectedItem();
    String categoria = (String) cbxCategoria.getSelectedItem();
    String MAX = txt_ubicacion.getText();
    //String estado = txt_estado.getText();
    //String cuerpo = txt_cuerpo.getText();
    //String reja = txt_reja.getText();
    //String tapa = txt_tapa.getText();

    // Verificar que todos los campos están llenos
    if (codigo.isEmpty() || descripcion.isEmpty() || nomproveedor == null || nomproveedor.isEmpty() || 
        categoria == null || categoria.isEmpty() || MAX.isEmpty() /*|| cuerpo.isEmpty() || reja.isEmpty() || tapa.isEmpty()*/) {
        JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
        return;
    }
    //int cuerp =Integer.parseInt(cuerpo);
    //int rej =Integer.parseInt(reja);
    //int tap =Integer.parseInt(tapa);
    
    /*if (cuerp <= 0 || rej <= 0 || tap <= 0) {
        JOptionPane.showMessageDialog(null, "El valor no puede ser 0");
    }*/else{
        if (num == 0) {
        int respuesta = CP.registrarProducto(codigo, descripcion, nomproveedor, categoria, MAX, "0","0", "0");
        if (respuesta > 0) {
            if (CP.verificarCodigoInventario(codigo) == 0) {
                CP.insertarProductoInventario(codigo);
            }

            listar();
            limpiar();
            iniciar();
            bt_actualizar.setEnabled(false);
            txt_descripcion.requestFocus();  // Esto mueve el foco al campo de texto de la descripción
        }
    } else {
        int row = jtb_productos.getSelectedRow();
        String codigo_old = jtb_productos.getValueAt(row, 0).toString();

        int respuesta = CP.actualizarProducto(codigo, descripcion, nomproveedor, categoria, codigo_old, MAX, "0","0", "0");
        if (respuesta > 0) {
            listar();
            limpiar();
            iniciar();
            num = 0;
            txt_descripcion.requestFocus();  // Esto mueve el foco al campo de texto de la descripción
        }
    }
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_productos = new javax.swing.JTable();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_nuevo = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxProveedor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cbxCategoria = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txt_ubicacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Productos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Folio del lote *");

        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });

        jtb_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_productos);

        bt_guardar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/guardar.png"))); // NOI18N
        bt_guardar.setText("Guardar");
        bt_guardar.setBorderPainted(false);
        bt_guardar.setContentAreaFilled(false);
        bt_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_guardarActionPerformed(evt);
            }
        });

        bt_actualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/modificar.png"))); // NOI18N
        bt_actualizar.setText("Modificar");
        bt_actualizar.setBorderPainted(false);
        bt_actualizar.setContentAreaFilled(false);
        bt_actualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_actualizarActionPerformed(evt);
            }
        });

        bt_eliminar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/eliminar.png"))); // NOI18N
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setBorderPainted(false);
        bt_eliminar.setContentAreaFilled(false);
        bt_eliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_nuevo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/ic_nuevo.png"))); // NOI18N
        bt_nuevo.setText("Nuevo");
        bt_nuevo.setBorderPainted(false);
        bt_nuevo.setContentAreaFilled(false);
        bt_nuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Descripción *");

        txt_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descripcionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Administración de Productos");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel3.setText("Llene la información respectiva. \"No dejes campos vacios\"");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Presentacion *");

        cbxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCategoriaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Valor *");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/categorias.png"))); // NOI18N
        jButton1.setText("Categorias");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txt_ubicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ubicacionActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Fecha *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_descripcion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbxCategoria, 0, 132, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(379, 379, 379))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(343, 343, 343)))
                        .addGap(274, 274, 274)))
                .addGap(61, 61, 61))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_nuevo)
                .addGap(51, 51, 51)
                .addComponent(bt_guardar)
                .addGap(52, 52, 52)
                .addComponent(bt_actualizar)
                .addGap(68, 68, 68)
                .addComponent(bt_eliminar)
                .addGap(51, 51, 51)
                .addComponent(jButton1)
                .addGap(149, 149, 149))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guardar)
                    .addComponent(bt_actualizar)
                    .addComponent(bt_eliminar)
                    .addComponent(bt_nuevo)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_ubicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ubicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ubicacionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Fmr_categorias f = new Fmr_categorias();
        contenedor.add(f);
        f.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxCategoriaActionPerformed

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = jtb_productos.getSelectedRowCount();
        if (fila < 1) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la tabla");
        } else {
            int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar?", "Eliminar Articulo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resp == 0) {
                if (CP.eliminarProducto(jtb_productos.getValueAt(jtb_productos.getSelectedRow(), 0).toString()) > 0) {
                    listar();
                    limpiar();
                    bt_eliminar.setEnabled(false);
                    bt_actualizar.setEnabled(false);
                    bt_guardar.setEnabled(false);
                }
            }
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_bt_guardarActionPerformed

    private void jtb_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_productosMouseClicked
        bt_actualizar.setEnabled(true);
        bt_eliminar.setEnabled(true);

        int row = jtb_productos.getSelectedRow();
        txt_codigo.setText(jtb_productos.getValueAt(row, 0).toString());
        txt_descripcion.setText(jtb_productos.getValueAt(row, 1).toString());
        cbxProveedor.setSelectedItem(jtb_productos.getValueAt(row, 2).toString());
        cbxCategoria.setSelectedItem(jtb_productos.getValueAt(row, 3).toString());
        txt_ubicacion.setText(jtb_productos.getValueAt(row, 4).toString());
        //txt_cuerpo.setText(jtb_productos.getValueAt(row, 5).toString());
        //txt_reja.setText(jtb_productos.getValueAt(row, 6).toString());
        //txt_tapa.setText(jtb_productos.getValueAt(row, 7).toString());
    }//GEN-LAST:event_jtb_productosMouseClicked

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void txt_estadoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }

    private void txt_cuerpotionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_productos;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_ubicacion;
    // End of variables declaration//GEN-END:variables
}
