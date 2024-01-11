package Formularios;

import Clases.Class_Excel;
import Clases.Cls_BuscarProductos;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Frm_BuscarProductos extends javax.swing.JInternalFrame {

    private final Cls_BuscarProductos CP;
    TableColumnModel columnModel;

    DefaultTableModel DT = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

    };

    public Frm_BuscarProductos() {
        initComponents();
        CP = new Cls_BuscarProductos();
        columnModel = tabla.getColumnModel();
        
        updateTableBasedOnSource();
    }
    
    private void updateTableBasedOnSource() {
        if(Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1 || Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1){
            if (Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1) {
                listarEntrada();
            } else if (Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1) {
                listarSalida();
            }
        }else{
            listar();
        }
        
    }

    private void listar() {
        tabla.setModel(CP.getDatosProductos());
    }
    
    private void listarEntrada() {
        tabla.setModel(CP.getDatosEntrada());
    }
    
    private void listarSalida() {
        tabla.setModel(CP.getDatosSalida());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg_busqueda = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jrb_nombre = new javax.swing.JRadioButton();
        jrb_codigo = new javax.swing.JRadioButton();
        txt_busqueda = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        excel_report = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle("Lista de Productos");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Búsqueda por:");

        jrb_nombre.setBackground(new java.awt.Color(255, 255, 255));
        bg_busqueda.add(jrb_nombre);
        jrb_nombre.setText("Fecha");
        jrb_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_nombreActionPerformed(evt);
            }
        });

        jrb_codigo.setBackground(new java.awt.Color(255, 255, 255));
        bg_busqueda.add(jrb_codigo);
        jrb_codigo.setText("Folio");
        jrb_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrb_codigoActionPerformed(evt);
            }
        });

        txt_busqueda.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_busquedaCaretUpdate(evt);
            }
        });
        txt_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busquedaActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setToolTipText("");
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        excel_report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/xls.png"))); // NOI18N
        excel_report.setText("Entradas/Salidas");
        excel_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excel_reportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrb_codigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jrb_nombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(excel_report)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jrb_nombre)
                    .addComponent(jrb_codigo)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excel_report))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_busquedaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_busquedaCaretUpdate
        if (jrb_nombre.isSelected()) {
            String inf = txt_busqueda.getText();
            if(Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1 || Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1){
                if (Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1) {
                    tabla.setModel(CP.getDatoE(1, inf));
                    Frm_Entrada.enviar = 0;
                    Frm_Entrada_sub.enviar = 0;
                    dispose();
                } else if (Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1) {
                    tabla.setModel(CP.getDatoS(1, inf));
                    Frm_Salida.enviar = 0;
                    Frm_Salida_sub.enviar = 0;
                    dispose();
                }
            }else{
                tabla.setModel(CP.getDatoP(1, inf));
            }
        }

        if (jrb_codigo.isSelected()) {
            String inf = txt_busqueda.getText();
            if(Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1 || Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1){
                if (Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1) {
                    tabla.setModel(CP.getDatoE(2, inf));
                } else if (Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1) {
                    tabla.setModel(CP.getDatoS(2, inf));
                }
            }else{
                tabla.setModel(CP.getDatoP(2, inf));
            }
        }

        if (txt_busqueda.getText().isEmpty()) {
            if(Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1 || Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1){
                if (Frm_Entrada.enviar == 1 || Frm_Entrada_sub.enviar == 1) {
                    tabla.setModel(CP.getDatosEntrada());
                } else if (Frm_Salida.enviar == 1 || Frm_Salida_sub.enviar == 1) {
                    tabla.setModel(CP.getDatosSalida());
                }
            }else{
                tabla.setModel(CP.getDatosProductos());
            }
        }
    }//GEN-LAST:event_txt_busquedaCaretUpdate

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (Frm_Entrada.enviar == 1) {
            int row = tabla.getSelectedRow();
            Frm_Entrada.txt_codigo.setText(tabla.getValueAt(row, 0).toString());
            Frm_Entrada.txt_descripcion.setText(tabla.getValueAt(row, 1).toString());
            Frm_Entrada.enviar = 0;
            dispose();
        } else if (Frm_Entrada_sub.enviar == 1) {
            int row = tabla.getSelectedRow();
            Frm_Entrada_sub.txt_codigo.setText(tabla.getValueAt(row, 0).toString());
            Frm_Entrada_sub.txt_descripcion.setText(tabla.getValueAt(row, 1).toString());
            Frm_Entrada_sub.enviar = 0;
            dispose();
        } else if (Frm_Salida_sub.enviar == 1) {
            int row = tabla.getSelectedRow();
            Frm_Salida_sub.txt_codigo.setText(tabla.getValueAt(row, 0).toString());
            Frm_Salida_sub.enviar = 0;
            dispose();
        }

    }//GEN-LAST:event_tablaMouseClicked

    private void jrb_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrb_codigoActionPerformed

    private void jrb_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrb_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrb_nombreActionPerformed

    private void txt_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busquedaActionPerformed

    private void excel_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excel_reportActionPerformed
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow >= 0) {
            String proCodigo = tabla.getValueAt(selectedRow, 0).toString(); // Asumiendo que el código del producto está en la columna 0.

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como"); // Título del diálogo
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Excel (*.xls)", "xls")); // Filtro para solo mostrar archivos Excel
            fileChooser.setSelectedFile(new File(proCodigo + "_Reporte.xls")); // Nombre de archivo sugerido

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String filePath = fileToSave.getAbsolutePath();
                if (!filePath.endsWith(".xls")) {
                    filePath += ".xls"; // Asegúrate de que la extensión sea .xls
                }

                Class_Excel classExcel = new Class_Excel();
                try {
                    classExcel.exportarAExcel(proCodigo, filePath);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error al guardar el archivo Excel: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un producto de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_excel_reportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bg_busqueda;
    private javax.swing.JButton excel_report;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrb_codigo;
    private javax.swing.JRadioButton jrb_nombre;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txt_busqueda;
    // End of variables declaration//GEN-END:variables
}
