package Formularios;

import Clases.Clas_Exportar;
import Clases.Cls_Entrada;
import Clases.Cls_Pdfcreator;
import static Formularios.Frm_Principal.contenedor;
import com.toedter.calendar.JTextFieldDateEditor;
import java.io.IOException;
import java.awt.Dimension;
import java.io.File;
import java.util.Date; 
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frm_Entrada extends javax.swing.JInternalFrame {

    Clas_Exportar objj;
    private final Cls_Entrada CP;
    private Frm_BuscarProductos currentBuscarProductosFrame;
    public static int enviar = 0;
    
    public Frm_Entrada() {
        initComponents();
        CP = new Cls_Entrada();
        listar();
        iniciar();
    }

    private void listar() {
        jtb_entrada.setModel(CP.getDatosEntradas());
    }

    private void iniciar() {
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
        if (codigo.isEmpty() || fechaa == null || txt_mermac.getText().isEmpty() || txt_mermar.getText().isEmpty() || txt_mermat.getText().isEmpty() || ret_cuerpo.getText().isEmpty() || ret_divisor.getText().isEmpty() || ret_tapa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, llene todos los campos.");
            return;
        }

        long d = fechaa.getTime();
        int mermac = Integer.parseInt(txt_mermac.getText());
        int mermar = Integer.parseInt(txt_mermar.getText());
        int mermat = Integer.parseInt(txt_mermat.getText());
        int rescuerpo = Integer.parseInt(ret_cuerpo.getText());
        int resreja = Integer.parseInt(ret_divisor.getText());
        int restapa = Integer.parseInt(ret_tapa.getText());

        java.sql.Date fecha_sql = new java.sql.Date(d);

        if (rescuerpo < mermac || resreja < mermar || restapa < mermat) {
            JOptionPane.showMessageDialog(null, "La merma no puede ser mayor que las entradas.");
        } else {
            String folio = CP.generarFolio(codigo, fecha_sql);
            if (!folio.isEmpty()) {
                int respuesta = CP.registrarEntrada(folio, codigo, fecha_sql, 0, 0, rescuerpo, resreja, restapa, mermac, mermar, mermat);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        txt_descripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jdc_fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_entrada = new javax.swing.JTable();
        jbt_buscar = new javax.swing.JButton();
        bt_nuevo = new javax.swing.JButton();
        jbt_guardar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnexportar = new javax.swing.JButton();
        cant_cuerpo = new javax.swing.JLabel();
        cant_reja = new javax.swing.JLabel();
        txt_mermac = new javax.swing.JTextField();
        txt_mermar = new javax.swing.JTextField();
        txt_mermat = new javax.swing.JTextField();
        cant_cuerpo1 = new javax.swing.JLabel();
        ret_divisor = new javax.swing.JTextField();
        cant_reja1 = new javax.swing.JLabel();
        ret_cuerpo = new javax.swing.JTextField();
        cant_cuerpo2 = new javax.swing.JLabel();
        ret_tapa = new javax.swing.JTextField();
        cant_cuerpo3 = new javax.swing.JLabel();
        pdf_button = new javax.swing.JToggleButton();

        setClosable(true);
        setTitle("Entrada");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Código del Producto *");

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Descripción del Producto *");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Fecha *");

        jdc_fecha.setDateFormatString("yyyy/MM/dd");
        ((JTextField) jdc_fecha.getDateEditor()).setEditable(false);
        jdc_fecha.getCalendarButton().setEnabled(false);

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
        jScrollPane1.setViewportView(jtb_entrada);

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

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Entrada de Productos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setText("Llene la información respectiva para la entrada de los productos.");

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

        cant_cuerpo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo.setText("Cuerpo(s) merma *");

        cant_reja.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_reja.setText("Divisor(es) merma *");

        txt_mermat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mermatActionPerformed(evt);
            }
        });

        cant_cuerpo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo1.setText("Tapa(s) merma *");

        cant_reja1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_reja1.setText("Divisor(es) recibidos *");

        cant_cuerpo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo2.setText("Cuerpo(s) recibidos *");

        cant_cuerpo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cant_cuerpo3.setText("Tapa(s) recibidas *");

        pdf_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        pdf_button.setText("Exportar PDF");
        pdf_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdf_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jbt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(61, 61, 61)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jdc_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(136, 136, 136)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(145, 145, 145)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cant_cuerpo2)
                                .addComponent(cant_reja1)
                                .addComponent(ret_divisor)
                                .addComponent(ret_cuerpo)
                                .addComponent(ret_tapa, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                            .addComponent(cant_cuerpo3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_mermar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cant_cuerpo)
                            .addComponent(cant_reja)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txt_mermat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                .addComponent(txt_mermac, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(cant_cuerpo1))))
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(bt_nuevo)
                .addGap(52, 52, 52)
                .addComponent(jbt_guardar)
                .addGap(47, 47, 47)
                .addComponent(btnexportar)
                .addGap(31, 31, 31)
                .addComponent(pdf_button)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cant_cuerpo1)
                            .addComponent(cant_cuerpo3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ret_tapa, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(txt_mermat))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cant_cuerpo)
                    .addComponent(cant_cuerpo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jdc_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(ret_cuerpo)
                    .addComponent(txt_mermac))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cant_reja)
                    .addComponent(cant_reja1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbt_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_descripcion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ret_divisor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(txt_mermar, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_codigo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbt_guardar)
                    .addComponent(bt_nuevo)
                    .addComponent(btnexportar)
                    .addComponent(pdf_button))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void txt_mermatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mermatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mermatActionPerformed

    private void pdf_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdf_buttonActionPerformed
        // Usa tu tabla jtb_entrada directamente

        // Crea un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar como"); // Título del diálogo

        // Sugerir un nombre de archivo predeterminado
        fileChooser.setSelectedFile(new File("salida.pdf"));

        // Filtro para que solo se muestren archivos .pdf
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Muestra el diálogo para guardar archivo
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath();
            if (!path.endsWith(".pdf")) {
                path += ".pdf";
            }

            Cls_Pdfcreator pdfCreator = new Cls_Pdfcreator();

            boolean result = pdfCreator.exportarPDF(jtb_entrada, path);

            if (result) {
                JOptionPane.showMessageDialog(null, "PDF generado con éxito en: " + path);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo generar el PDF.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_pdf_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JButton btnexportar;
    private javax.swing.JLabel cant_cuerpo;
    private javax.swing.JLabel cant_cuerpo1;
    private javax.swing.JLabel cant_cuerpo2;
    private javax.swing.JLabel cant_cuerpo3;
    private javax.swing.JLabel cant_reja;
    private javax.swing.JLabel cant_reja1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbt_buscar;
    private javax.swing.JButton jbt_guardar;
    private com.toedter.calendar.JDateChooser jdc_fecha;
    private javax.swing.JTable jtb_entrada;
    private javax.swing.JToggleButton pdf_button;
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
