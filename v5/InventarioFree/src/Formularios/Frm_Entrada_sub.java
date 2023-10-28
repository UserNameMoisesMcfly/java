package Formularios;

import Clases.Clas_Exportar;
import Clases.Cls_Entrada;
import java.io.IOException;
import static Formularios.Frm_SubAdmin.contenedor;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Dimension;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Frm_Entrada_sub extends javax.swing.JInternalFrame {

    Clas_Exportar objj;
    private final Cls_Entrada CP;
    private Frm_BuscarProductos currentBuscarProductosFrame;
    public static int enviar = 0;
    
    public Frm_Entrada_sub() {
        initComponents();
        CP = new Cls_Entrada();
        listar();
        iniciar();
    }

    private void listar() {
        jtb_entrada.setModel(CP.getDatosEntradas());
    }

    private void iniciar() {
        //txt_cantidad.setEnabled(false);
        txt_codigo.setEnabled(false);
        jdc_fecha.setEnabled(false);
        jbt_buscar.setEnabled(false);
        jbt_guardar.setEnabled(false);
        txt_mermac.setEnabled(false);
        txt_mermar.setEnabled(false);
        txt_mermat.setEnabled(false);
        ret_cuerpo.setEnabled(false);
        ret_divisor.setEnabled(false);
        ret_tapa.setEnabled(false);

    }
    
    private void activar() {
        //txt_cantidad.setEnabled(true);
        txt_codigo.setEnabled(true);
        jdc_fecha.setEnabled(true);
        jbt_buscar.setEnabled(true);
        jbt_guardar.setEnabled(true);
        jbt_guardar.setEnabled(true);
        jdc_fecha.getCalendarButton().setEnabled(false);
        txt_mermac.setEnabled(true);
        txt_mermar.setEnabled(true);
        txt_mermat.setEnabled(true);
        ret_cuerpo.setEnabled(true);
        ret_divisor.setEnabled(true);
        ret_tapa.setEnabled(true);

    }

    private void limpiar() {
        
        txt_mermac.setText("");
        txt_mermar.setText("");
        txt_mermat.setText("");
        ret_cuerpo.setText("");
        ret_divisor.setText("");
        ret_tapa.setText("");
        txt_descripcion.setText("");
        jtb_entrada.clearSelection();
    }
    
    private void guardar() {
        String codigo = txt_codigo.getText();
        Date fechaa = jdc_fecha.getDate();
        long d = fechaa.getTime();
        int mermac = Integer.parseInt(txt_mermac.getText());
        int mermar = Integer.parseInt(txt_mermar.getText());
        int mermat = Integer.parseInt(txt_mermat.getText());
        int rescuerpo = Integer.parseInt(ret_cuerpo.getText());
        int resreja = Integer.parseInt(ret_divisor.getText());
        int restapa = Integer.parseInt(ret_tapa.getText());

        java.sql.Date fecha_sql = new java.sql.Date(d);

        if (rescuerpo <= mermac || resreja <= mermar || restapa <= mermat) {
            JOptionPane.showMessageDialog(null, "Merma no puede ser mayor a las entradas");
        } else {
            String folio = CP.generarFolio(codigo, fecha_sql);
            if (!folio.isEmpty()) {
                int respuesta = CP.registrarEntrada(folio, codigo, fecha_sql, 0, rescuerpo, resreja, restapa, mermac, mermar, mermat);
                if (respuesta > 0) {
                    listar();
                    limpiar();
                    iniciar();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jdc_fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtb_entrada = new javax.swing.JTable();
        jbt_buscar = new javax.swing.JButton();
        bt_nuevo = new javax.swing.JButton();
        jbt_guardar = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnexportar = new javax.swing.JButton();
        cant_cuerpo8 = new javax.swing.JLabel();
        cant_reja4 = new javax.swing.JLabel();
        txt_mermac = new javax.swing.JTextField();
        txt_mermar = new javax.swing.JTextField();
        txt_mermat = new javax.swing.JTextField();
        cant_cuerpo9 = new javax.swing.JLabel();
        ret_divisor = new javax.swing.JTextField();
        cant_reja5 = new javax.swing.JLabel();
        ret_cuerpo = new javax.swing.JTextField();
        cant_cuerpo10 = new javax.swing.JLabel();
        ret_tapa = new javax.swing.JTextField();
        cant_cuerpo11 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Entrada");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Código del Producto *");

        txt_codigo.setEditable(false);
        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });

        txt_descripcion.setEditable(false);
        txt_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descripcionActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("Descripción del Producto *");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Fecha *");

        jdc_fecha.setDateFormatString("yyyy/MM/dd");
        ((JTextFieldDateEditor) jdc_fecha.getDateEditor()).setEditable(false);
        
        jtb_entrada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_entrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_entradaMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jtb_entrada);

        jbt_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/ic_consultas.png"))); // NOI18N
        jbt_buscar.setBorderPainted(false);
        jbt_buscar.setContentAreaFilled(false);
        jbt_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_buscarActionPerformed(evt);
            }
        });

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

        jbt_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/ic_grabar.png"))); // NOI18N
        jbt_guardar.setText("Registrar Entrada");
        jbt_guardar.setBorderPainted(false);
        jbt_guardar.setContentAreaFilled(false);
        jbt_guardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbt_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbt_guardarActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setText("Entrada de Productos");

        jLabel22.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel22.setText("Llene la información respectiva para la entrada de los productos.");

        btnexportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/excell.png"))); // NOI18N
        btnexportar.setText("Reporte Excel");
        btnexportar.setBorderPainted(false);
        btnexportar.setContentAreaFilled(false);
        btnexportar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        cant_cuerpo8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo8.setText("Cuerpo(s) merma *");

        cant_reja4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_reja4.setText("Divisor(es) merma *");

        txt_mermat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mermatActionPerformed(evt);
            }
        });

        cant_cuerpo9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo9.setText("Tapa(s) merma *");

        cant_reja5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_reja5.setText("Divisor(es) a retirar *");

        cant_cuerpo10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo10.setText("Cuerpo(s) a retirar *");

        cant_cuerpo11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo11.setText("Tapa(s) a retirar *");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cant_cuerpo10)
                                .addComponent(cant_reja5)
                                .addComponent(ret_divisor)
                                .addComponent(ret_cuerpo)
                                .addComponent(ret_tapa, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                            .addComponent(cant_cuerpo11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_mermar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cant_cuerpo8)
                            .addComponent(cant_reja4)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_mermat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addComponent(txt_mermac, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                            .addComponent(cant_cuerpo9))))
                .addGap(77, 77, 77))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(259, 259, 259)
                .addComponent(bt_nuevo)
                .addGap(52, 52, 52)
                .addComponent(jbt_guardar)
                .addGap(47, 47, 47)
                .addComponent(btnexportar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cant_cuerpo9)
                            .addComponent(cant_cuerpo11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ret_tapa)
                            .addComponent(txt_mermat, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cant_cuerpo8)
                    .addComponent(cant_cuerpo10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdc_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(txt_mermac, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(ret_cuerpo))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(cant_reja4)
                    .addComponent(cant_reja5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_descripcion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbt_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_mermar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ret_divisor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbt_guardar)
                    .addComponent(bt_nuevo)
                    .addComponent(btnexportar))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void jtb_entradaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_entradaMouseClicked

    }//GEN-LAST:event_jtb_entradaMouseClicked

    private void jbt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_buscarActionPerformed
        enviar = 1;
        try {
            // Cierra el JInternalFrame actualmente abierto si existe uno.
            if (currentBuscarProductosFrame != null) {
                currentBuscarProductosFrame.dispose();
            }

            // Abre un nuevo JInternalFrame.
            currentBuscarProductosFrame = new Frm_BuscarProductos();
            Frm_Principal.contenedor.add(currentBuscarProductosFrame);
            Dimension desktopSize = contenedor.getSize();
            Dimension FrameSize = currentBuscarProductosFrame.getSize();
            currentBuscarProductosFrame.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            currentBuscarProductosFrame.toFront();
            currentBuscarProductosFrame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }//GEN-LAST:event_jbt_buscarActionPerformed

    private void bt_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoActionPerformed
        activar();
        limpiar();
        jdc_fecha.setDate(new Date());
        jbt_guardar.setEnabled(true);
    }//GEN-LAST:event_bt_nuevoActionPerformed

    private void jbt_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbt_guardarActionPerformed
        guardar();
    }//GEN-LAST:event_jbt_guardarActionPerformed

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        try {
            objj = new Clas_Exportar();
            objj.exportarExcel(jtb_entrada);
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_btnexportarActionPerformed

    private void txt_mermatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mermatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mermatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JButton btnexportar;
    private javax.swing.JLabel cant_cuerpo10;
    private javax.swing.JLabel cant_cuerpo11;
    private javax.swing.JLabel cant_cuerpo8;
    private javax.swing.JLabel cant_cuerpo9;
    private javax.swing.JLabel cant_reja4;
    private javax.swing.JLabel cant_reja5;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton jbt_buscar;
    private javax.swing.JButton jbt_guardar;
    private com.toedter.calendar.JDateChooser jdc_fecha;
    private javax.swing.JTable jtb_entrada;
    private javax.swing.JTextField ret_cuerpo;
    private javax.swing.JTextField ret_divisor;
    private javax.swing.JTextField ret_tapa;
    public static javax.swing.JTextField txt_codigo;
    public static javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_mermac;
    private javax.swing.JTextField txt_mermar;
    private javax.swing.JTextField txt_mermat;
    // End of variables declaration//GEN-END:variables
}