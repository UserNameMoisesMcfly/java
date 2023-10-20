package Formularios;

import Clases.Clas_Exportar;
import Clases.Cls_Productos;
import Clases.Cls_Tareas;
import static Formularios.Frm_Principal.contenedor;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumnModel;

public class Frm_Tareas_admin extends javax.swing.JInternalFrame {

    Clas_Exportar objj;
    private final Cls_Tareas CP;
    TableColumnModel columnModel;
    int num = 0;

    public Frm_Tareas_admin() {
        initComponents();
        CP = new Cls_Tareas();
        //CP.ConsultarEstado(cbx_EstadoTarea);            ///////////////////////////////////////////////////////////
        //CP.ConsultarCategorias(cbxCategoria_Tarea);           //////////////////////////////////////
        columnModel = jtb_tareas.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        // bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
    }

    private void listar() {
        jtb_tareas.setModel(CP.getDatosProductos());
        columnModel.getColumn(1).setPreferredWidth(600);
    }

    private void iniciar() {
        txt_nombre.setEnabled(false);
        txt_herramineta.setEnabled(false);
        cbx_EstadoTarea.setEnabled(false);
        //cbxCategoria_Tarea.setEnabled(false);
        txt_numSerie.setEnabled(false);
    }

    private void activar() {
        txt_nombre.setEnabled(true);
        txt_herramineta.setEnabled(true);
        cbx_EstadoTarea.setEnabled(true);
        //cbxCategoria_Tarea.setEnabled(true);
        txt_numSerie.setEnabled(true);
        txt_nombre.requestFocus();
    }

    private void limpiar() {
        txt_nombre.setText("");
        txt_herramineta.setText("");
        txt_nombre.requestFocus();
        jtb_tareas.clearSelection();
        txt_numSerie.setText("");
    }

    private void guardar() {
        String nombre = txt_nombre.getText();
        String herramineta = txt_herramineta.getText();
        String estadoTarea = (String) cbx_EstadoTarea.getSelectedItem();//////////////////////
        //  String categoria = "";//(String) cbxCategoria_Tarea.getSelectedItem();///////////////////////
        String numSerie = txt_numSerie.getText();//////////////////////

        if (num == 0) {
            int respuesta = CP.registrarProducto(nombre, herramineta, numSerie, estadoTarea);/////////////////////
            if (respuesta > 0) {
                //if (CP.verificarCodigoInventario(codigo) == 0) { }
                //CP.insertarProductoInventario(codigo);
                listar();
                limpiar();
                iniciar();
                bt_actualizar.setEnabled(false);
            }
        } else {

            int row = jtb_tareas.getSelectedRow();
            String codigo_old = jtb_tareas.getValueAt(row, 0).toString();

            int respuesta = CP.actualizarTarea(numSerie, nombre, herramineta, estadoTarea, codigo_old); //////////
            if (respuesta > 0) {
                listar();
                limpiar();
                iniciar();
                num = 0;
            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_tareas = new javax.swing.JTable();
        bt_guardar = new javax.swing.JButton();
        bt_actualizar = new javax.swing.JButton();
        bt_nuevaTarea = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_herramineta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        exp_Excel = new javax.swing.JButton();
        txt_numSerie = new javax.swing.JTextField();
        cbx_EstadoTarea = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Tasks");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Nombre de la persona *");

        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        jtb_tareas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_tareas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_tareasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_tareas);

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

        bt_nuevaTarea.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bt_nuevaTarea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/ic_nuevo.png"))); // NOI18N
        bt_nuevaTarea.setText("Nueva Tarea");
        bt_nuevaTarea.setBorderPainted(false);
        bt_nuevaTarea.setContentAreaFilled(false);
        bt_nuevaTarea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bt_nuevaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevaTareaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Herramineta utilizada *");

        txt_herramineta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_herraminetaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Administración de Tareas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel3.setText("Llene la información respectiva. \"No dejes campos vacios\"");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Numero de serie / codigo *");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Estado de la tarea *");

        exp_Excel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        exp_Excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/excell.png"))); // NOI18N
        exp_Excel.setText("Exportar Excel");
        exp_Excel.setBorderPainted(false);
        exp_Excel.setContentAreaFilled(false);
        exp_Excel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exp_Excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exp_ExcelActionPerformed(evt);
            }
        });

        txt_numSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numSerieActionPerformed(evt);
            }
        });

        cbx_EstadoTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hecho", "Pendiente" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(bt_nuevaTarea)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                        .addComponent(bt_guardar)
                        .addGap(104, 104, 104)
                        .addComponent(bt_actualizar)
                        .addGap(88, 88, 88)
                        .addComponent(exp_Excel)
                        .addGap(110, 110, 110))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(txt_herramineta, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel3))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txt_numSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(cbx_EstadoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel2))
                        .addContainerGap(131, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_herramineta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_numSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_EstadoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guardar)
                    .addComponent(bt_actualizar)
                    .addComponent(bt_nuevaTarea)
                    .addComponent(exp_Excel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_guardarActionPerformed
        guardar();

    }//GEN-LAST:event_bt_guardarActionPerformed

    private void jtb_tareasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_tareasMouseClicked
        bt_actualizar.setEnabled(true);
        //bt_eliminar.setEnabled(true);

        int row = jtb_tareas.getSelectedRow();
        txt_nombre.setText(jtb_tareas.getValueAt(row, 1).toString());
        txt_herramineta.setText(jtb_tareas.getValueAt(row, 2).toString());
        cbx_EstadoTarea.setSelectedItem(jtb_tareas.getValueAt(row, 3).toString());
        //cbxCategoria_Tarea.setSelectedItem(jtb_productos.getValueAt(row, 3).toString());
        txt_numSerie.setText(jtb_tareas.getValueAt(row, 0).toString());

    }//GEN-LAST:event_jtb_tareasMouseClicked

    private void bt_nuevaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevaTareaActionPerformed
        activar();
        limpiar();
        bt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevaTareaActionPerformed

    private void bt_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_actualizarActionPerformed
        num = 1;
        activar();
        bt_actualizar.setEnabled(false);
        bt_guardar.setEnabled(true);
        // bt_eliminar.setEnabled(false);
    }//GEN-LAST:event_bt_actualizarActionPerformed

    private void txt_herraminetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_herraminetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_herraminetaActionPerformed

    private void exp_ExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exp_ExcelActionPerformed
        try {
            objj = new Clas_Exportar();
            objj.exportarExcel(jtb_tareas);
        } catch (Exception e) {
            System.out.println("Error en " + e);
        }
    }//GEN-LAST:event_exp_ExcelActionPerformed

    private void txt_numSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numSerieActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevaTarea;
    private javax.swing.JComboBox<String> cbx_EstadoTarea;
    private javax.swing.JButton exp_Excel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_tareas;
    private javax.swing.JTextField txt_herramineta;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_numSerie;
    // End of variables declaration//GEN-END:variables
}
