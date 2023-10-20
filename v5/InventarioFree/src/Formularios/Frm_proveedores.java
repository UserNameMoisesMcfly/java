package Formularios;

import Conexion.Conectar;
import static Formularios.Frm_Principal.contenedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Frm_proveedores extends javax.swing.JInternalFrame {

    Conectar cc = new Conectar();
    Connection con = cc.getConnection();

    public Frm_proveedores() {
        initComponents();

        mostrarProveedores();
    }

    public void mostrarProveedores() {
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            jtProveedores.setModel(modelo);

            PreparedStatement pr = null;
            ResultSet sr = null;

            String sqr = "SELECT nombre_pro,correo FROM proveedor";
            pr = con.prepareStatement(sqr);
            sr = pr.executeQuery();

            ResultSetMetaData srMd = sr.getMetaData();
            int cantidadColumnas = srMd.getColumnCount();

            modelo.addColumn("proveedor");
            //modelo.addColumn("RFC");
            //modelo.addColumn("razon social");
            //modelo.addColumn("telefono");
            modelo.addColumn("e-mail");

            while (sr.next()) {

                Object[] filas = new Object[cantidadColumnas];

                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = sr.getObject(i + 1);
                }
                modelo.addRow(filas);

            }

        } catch (SQLException Ex) {
            JOptionPane.showMessageDialog(null, "No se logro mostrar" + Ex.getMessage());
        }
    }

    public void agregarProveedor() {

        String sql = "INSERT INTO proveedor(fecha_altaa,nombre_pro,correo) values(?,?,?)";

        try {
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, ((JTextField) jdFecha.getDateEditor().getUiComponent()).getText());
            pst.setString(2, txtProveedor.getText());
            pst.setString(3, email.getText());
            //pst.setString(3, txtRFC.getText());
            //pst.setString(4, txtRazonSocial.getText());
            //pst.setString(5, txtTelefono.getText());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Registro exitoso");

            mostrarProveedores();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de registro" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtProveedores = new javax.swing.JTable();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        btn_Add = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_Delate = new javax.swing.JButton();
        btn_Clean = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Proveedores");

        jtProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre Proveedor", "RFC", "Razon social", "Telefono", "e - mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtProveedores);

        jdFecha.setDateFormatString("yyyy/MM/dd");

        jLabel1.setText("Fecha de alta:");

        jLabel2.setText("Nombre:");

        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });

        jLabel6.setText("e-mail:");

        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/adduser.png"))); // NOI18N
        btn_Add.setText("Agregar");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/modificar.png"))); // NOI18N
        btn_Update.setText("Modificar");
        btn_Update.setEnabled(false);
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_Delate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/eliminar.png"))); // NOI18N
        btn_Delate.setText("Eliminar");
        btn_Delate.setEnabled(false);
        btn_Delate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DelateActionPerformed(evt);
            }
        });

        btn_Clean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/ic_limpiar.png"))); // NOI18N
        btn_Clean.setText("Limpiar");
        btn_Clean.setEnabled(false);
        btn_Clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CleanActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ITO/proveedor.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(112, 112, 112)
                            .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(btn_Delate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(49, 49, 49)
                            .addComponent(btn_Clean, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(85, 85, 85)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jdFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Delate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Clean))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        agregarProveedor();
    }//GEN-LAST:event_btn_AddActionPerformed

    private void jtProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtProveedoresMouseClicked
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conectar objcon = new Conectar();
            Connection connn = objcon.getConnection();

            int filaa = jtProveedores.getSelectedRow();
            String nombre_pro = jtProveedores.getValueAt(filaa, 0).toString();

            ps = connn.prepareStatement("SELECT * FROM proveedor WHERE nombre_pro=?");
            ps.setString(1, nombre_pro);
            rs = ps.executeQuery();

            while (rs.next()) {
                txtProveedor.setText(rs.getString("nombre_pro"));
                // txtRFC.setText(rs.getString("RFC"));
                // txtRazonSocial.setText(rs.getString("razon_social"));
                //txtTelefono.setText(rs.getString("telefono"));
                email.setText(rs.getString("correo"));
            }

        } catch (SQLException exx) {
            JOptionPane.showMessageDialog(null, "no se pudo realizar la consulta" + exx.toString());
        }

        btn_Update.setEnabled(true);
        btn_Delate.setEnabled(true);
        btn_Clean.setEnabled(true);

    }//GEN-LAST:event_jtProveedoresMouseClicked

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed
        int filaa = jtProveedores.getSelectedRow();
        String nombre_pro = jtProveedores.getValueAt(filaa, 0).toString();

        String provedor = txtProveedor.getText();
        //String rfc = txtRFC.getText();
        //String razon = txtRazonSocial.getText();
        //String tel = txtTelefono.getText();
        String eemail = email.getText();

        try {

            PreparedStatement modi = con.prepareStatement("UPDATE proveedor SET nombre_pro='" + provedor + "', correo='" + eemail + "' WHERE nombre_pro ='" + nombre_pro + "' ");
            modi.executeUpdate();

            mostrarProveedores();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la actualización de los datos" + ex.toString());

        }
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_CleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CleanActionPerformed
        Frm_proveedores f = new Frm_proveedores();
        contenedor.add(f);
        f.show();

    }//GEN-LAST:event_btn_CleanActionPerformed

    private void btn_DelateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DelateActionPerformed
        PreparedStatement qs = null;

        try {

            Conectar objcon = new Conectar();
            Connection conn = objcon.getConnection();

            int Fila = jtProveedores.getSelectedRow();
            String nombre_pro = jtProveedores.getValueAt(Fila, 0).toString();

            qs = conn.prepareStatement("DELETE FROM proveedor WHERE  nombre_pro=?");
            qs.setString(1, nombre_pro);
            qs.execute();

            mostrarProveedores();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro eliminar el usuario! " + ex.toString());

        }
    }//GEN-LAST:event_btn_DelateActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_proveedores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_proveedores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Clean;
    private javax.swing.JButton btn_Delate;
    private javax.swing.JButton btn_Update;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JTable jtProveedores;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables
}
