package Formularios;


import Clases.Clas_Exportar;
import Clases.Cls_Pdfcreator;
import Clases.Cls_Salida;
import static Formularios.Frm_SubAdmin.contenedor;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;

public class Frm_Salida_sub extends javax.swing.JInternalFrame {

    Clas_Exportar objj;
    private final Cls_Salida CP;
    TableColumnModel columnModel;
    private Frm_BuscarProductos currentBuscarProductosFrame;
    public static int enviar = 0;

    public Frm_Salida_sub() {
        initComponents();
        CP = new Cls_Salida();
        columnModel = jtb_salida.getColumnModel();
        listar();
        iniciar();
        
        
                                    /*
                                    txt_codigo.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            try {
                                                int codigoBarras = Integer.parseInt(txt_codigo.getText());
                                                int resultado = CP.registrarCodigoBarras(codigoBarras);

                                                if (resultado > 0) {
                                                    JOptionPane.showMessageDialog(null, "Código de barras registrado con éxito.");
                                                    // Aquí puedes agregar más acciones si necesitas realizar algo después del registro exitoso
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Error al registrar el código de barras.");
                                                }
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Formato de código de barras no válido.");
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                                            } finally {
                                                txt_codigo.setText(""); // Limpiar el campo de texto después del registro o en caso de error.
                                            }
                                        }
                                    });
                                    */
                                    
                                    /*
                                    txt_codigo.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            String codigoBarrasString = txt_codigo.getText();
                                            try {
                                                // Verificar si el código de barras contiene solo dígitos
                                                if (codigoBarrasString.matches("\\d+")) {
                                                    int codigoBarras = Integer.parseInt(codigoBarrasString);
                                                    int resultado = CP.registrarCodigoBarras(codigoBarras);

                                                    if (resultado > 0) {
                                                        JOptionPane.showMessageDialog(null, "Código de barras registrado con éxito.");
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Error al registrar el código de barras.");
                                                    }
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Formato de código de barras no válido. Solo se permiten números.");
                                                }
                                            } catch (NumberFormatException e) {
                                                JOptionPane.showMessageDialog(null, "Formato de código de barras no válido.");
                                            } catch (Exception e) {
                                                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                                            } finally {
                                                txt_codigo.setText(""); // Limpiar el campo de texto después del registro o en caso de error.
                                            }
                                        }
                                    });
                                    */
                                    
                                    
                                                    txt_codigo.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent evt) {
                                                            String codigoBarrasString = txt_codigo.getText();
                                                            try {
                                                                // Verificar si el código de barras contiene solo dígitos y tiene una longitud adecuada
                                                                if (codigoBarrasString.matches("\\d+") && codigoBarrasString.length() <= 100) {
                                                                    int resultado = CP.registrarCodigoBarras(codigoBarrasString);

                                                                    if (resultado > 0) {
                                                                        JOptionPane.showMessageDialog(null, "Código de barras registrado con éxito.");
                                                                        listar(); // Actualizar la tabla después de un registro exitoso
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Error al registrar el código de barras.");
                                                                    }
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Formato de código de barras no válido. Debe contener hasta 100 dígitos.");
                                                                }
                                                            } catch (Exception e) {
                                                                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                                                            } finally {
                                                                txt_codigo.setText(""); // Limpiar el campo de texto después del registro o en caso de error.
                                                            }
                                                        }
                                                    });

    }

    private void listar() {
        jtb_salida.setModel(CP.getDatosSalida());
        //columnModel.getColumn(3).setPreferredWidth(350);
    }

    private void iniciar() {
        txt_codigo.setEnabled(true);
    }

    /*private void activar() {
        txt_merma.setEnabled(true);
        txt_descripcion.setEnabled(true);
        jdc_fecha.setEnabled(true);
        jdc_fecha.getCalendarButton().setEnabled(false);
        jbt_buscar.setEnabled(true);
        jbt_guardar.setEnabled(true);
    }

    private void limpiar() {
        txt_codigo.setText("");
        txt_descripcion.setText("");
        jtb_salida.clearSelection();
        txt_merma.setText("");
    }

    private void guardar() throws SQLException {
        String entID = txt_codigo.getText();
        String descripcon = txt_descripcion.getText();
        int merma = Integer.parseInt(txt_merma.getText());
        Date fechaa = jdc_fecha.getDate();
        long d = fechaa.getTime();
        
        java.sql.Date fecha_sql = new java.sql.Date(d);
        
        String folio = CP.generarFolio(entID, fecha_sql);
        int entId = CP.obtenerId(entID);
        int tarima = CP.restaMerma(entID, merma);
        
        int respuesta = CP.registrarSalida(folio, fecha_sql, entId, descripcon, merma, tarima);
                if (respuesta > 0) {
                    listar();
                    limpiar();
                    iniciar();

                }
        /*int stock = CP.verificarStock(codigo);

        if (cantidad > stock) {
            JOptionPane.showMessageDialog(null, "¡No hay stock suficiente!");
            txt_cantidad.setText("");
            txt_cantidad.requestFocus();
        } else {
            if (num == 0) {
                
            }
        }
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtb_salida = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Salida");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Código del Producto *");

        txt_codigo.setEditable(true);
        txt_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codigoActionPerformed(evt);
            }
        });

        jtb_salida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_salida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_salidaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtb_salida);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Salida de Productos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jLabel7.setText("Llene la información respectiva para la salida de los productos.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jLabel6))
                            .addGap(535, 535, 535))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void jtb_salidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_salidaMouseClicked

    }//GEN-LAST:event_jtb_salidaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtb_salida;
    public static javax.swing.JTextField txt_codigo;
    // End of variables declaration//GEN-END:variables
}
