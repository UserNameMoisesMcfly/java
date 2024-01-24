package Formularios;

import Clases.Cls_Barras;
import Clases.Cls_Pdfcreator;
import Clases.Cls_Productos;
import Clases.Cls_Remision;
import static Formularios.Frm_Principal.contenedor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
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
        columnModel = jtb_remision.getColumnModel();
        listar();
        iniciar();
        bt_actualizar.setEnabled(false);
        bt_eliminar.setEnabled(false);
        bt_guardar.setEnabled(false);
       
        
    }

    private void listar() {
        jtb_productos.setModel(CP.getDatosProductos());
        jtb_remision.setModel(CP.getDatosRemision());
        columnModel.getColumn(1).setPreferredWidth(600);
        
        
    }
    

    private void iniciar() {
        txt_codigo.setEnabled(false);
        txt_descripcion.setEnabled(false);
        cbxProveedor.setEnabled(false);
        cbxCategoria.setEnabled(false);
        txt_ubicacion.setEnabled(false);
        
        bt_remision.setEnabled(false);
        
        //Remisiones
        txt_origen.setEnabled(false);
        txt_no_pro.setEnabled(false);
        txt_almacen.setEnabled(false);
        txt_pedido.setEnabled(false);
        txt_destino.setEnabled(false);
        txt_cliente.setEnabled(false);
        txt_linea.setEnabled(false);
        txt_autoriza.setEnabled(false);
        txt_transporte.setEnabled(false);
        txt_placas.setEnabled(false);
        txt_operador.setEnabled(false);
        txt_solicita.setEnabled(false);
        
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
    
    private void activar_remision(){
        
        //Remisiones
        bt_remision.setEnabled(true);
        
        txt_origen.setEnabled(true);
        txt_no_pro.setEnabled(true);
        txt_almacen.setEnabled(true);
        txt_pedido.setEnabled(true);
        txt_destino.setEnabled(true);
        txt_cliente.setEnabled(true);
        txt_linea.setEnabled(true);
        txt_autoriza.setEnabled(true);
        txt_transporte.setEnabled(true);
        txt_placas.setEnabled(true);
        txt_operador.setEnabled(true);
        txt_solicita.setEnabled(true);
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
    
    private void limpiar_remision(){
        
        //Remisiones
        txt_origen.setText("");
        txt_no_pro.setText("");
        txt_almacen.setText("");
        txt_pedido.setText("");
        txt_destino.setText("");
        txt_cliente.setText("");
        txt_linea.setText("");
        txt_autoriza.setText("");
        txt_transporte.setText("");
        txt_placas.setText("");
        txt_operador.setText("");
        txt_solicita.setText("");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jtb_remision = new javax.swing.JTable();
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
        barrasCode = new javax.swing.JButton();
        bt_remision = new javax.swing.JButton();
        txt_origen = new javax.swing.JTextField();
        txt_no_pro = new javax.swing.JTextField();
        txt_almacen = new javax.swing.JTextField();
        txt_pedido = new javax.swing.JTextField();
        txt_destino = new javax.swing.JTextField();
        txt_cliente = new javax.swing.JTextField();
        activar_remision = new javax.swing.JButton();
        txt_linea = new javax.swing.JTextField();
        txt_autoriza = new javax.swing.JTextField();
        txt_transporte = new javax.swing.JTextField();
        txt_placas = new javax.swing.JTextField();
        txt_operador = new javax.swing.JTextField();
        txt_solicita = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

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

        jScrollPane2.setEnabled(false);

        jtb_remision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtb_remision.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtb_remisionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtb_remision);

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

        barrasCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Mensaje.png"))); // NOI18N
        barrasCode.setText("Codigo");
        barrasCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barrasCodeActionPerformed(evt);
            }
        });

        bt_remision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pdf.png"))); // NOI18N
        bt_remision.setText("Remision");
        bt_remision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_remisionActionPerformed(evt);
            }
        });

        txt_origen.setText("ORIGEN");
        txt_origen.setToolTipText("");
        txt_origen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_origenActionPerformed(evt);
            }
        });

        txt_no_pro.setText("No. PROVEEDOR");

        txt_almacen.setText("ALMACEN");

        txt_pedido.setText("PEDIDO");

        txt_destino.setText("DESTINO");
        txt_destino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_destinoActionPerformed(evt);
            }
        });

        txt_cliente.setText("CLIENTE");

        activar_remision.setText("Activar");
        activar_remision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activar_remisionActionPerformed(evt);
            }
        });

        txt_linea.setText("LINEA");

        txt_autoriza.setText("AUTORIZA");

        txt_transporte.setText("TRANSPORTE");

        txt_placas.setText("PLACAS");

        txt_operador.setText("OPERADOR");

        txt_solicita.setText("SOLICITA CLIENTE");

        jLabel7.setText("Origen");

        jLabel9.setText("No. Proveedor");

        jLabel10.setText("Almacen");

        jLabel11.setText("Pedido");

        jLabel12.setText("Destino");

        jLabel13.setText("Cliente");

        jLabel14.setText("Linea");

        jLabel15.setText("Autoriza");

        jLabel16.setText("Transporte");

        jLabel17.setText("Placas");

        jLabel18.setText("Operador");

        jLabel19.setText("Solicita Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 33, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_nuevo)
                        .addGap(51, 51, 51)
                        .addComponent(bt_guardar)
                        .addGap(52, 52, 52)
                        .addComponent(bt_actualizar)
                        .addGap(68, 68, 68)
                        .addComponent(bt_eliminar)
                        .addGap(51, 51, 51)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(barrasCode, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(activar_remision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_remision, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                    .addGap(31, 31, 31))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel4)
                                                .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(41, 41, 41)
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(42, 42, 42))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(txt_descripcion))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(376, 376, 376))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(238, 238, 238)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_almacen, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_origen, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_no_pro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_placas)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_transporte, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_autoriza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_operador, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(325, 325, 325))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_origen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_destino, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_transporte, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_no_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_placas, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_operador, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(activar_remision, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(bt_remision, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_ubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_pedido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_autoriza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6)))))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_guardar)
                    .addComponent(bt_actualizar)
                    .addComponent(bt_eliminar)
                    .addComponent(bt_nuevo)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(barrasCode))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 521, Short.MAX_VALUE)
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
        
        // Formatear la fecha actual y establecerla en txt_ubicacion
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); // Puedes cambiar el formato de fecha si lo necesitas
        String fechaActual = formatoFecha.format(new Date());
        txt_ubicacion.setText(fechaActual);
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
        
        String codigoProducto = jtb_productos.getValueAt(row, 0).toString();
        Cls_Barras generadorBarras = new Cls_Barras();
        try {
            BufferedImage imagenCodigoBarras = generadorBarras.generateBarcode(codigoProducto);
        } catch (IOException ex) {
            Logger.getLogger(Frm_Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jtb_productosMouseClicked

    private void txt_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codigoActionPerformed

    private void barrasCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barrasCodeActionPerformed
        try {
        // Obtener el código del producto seleccionado
        int row = jtb_productos.getSelectedRow();
        String proCodigo = jtb_productos.getValueAt(row, 0).toString();

        // Generar el código de barras
        Cls_Barras generadorBarras = new Cls_Barras();
        BufferedImage barcodeImage = generadorBarras.generateBarcode(proCodigo);

        // Mostrar el file chooser para guardar la imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar código de barras");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Image", "png");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".png")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".png");
            }
            
            // Guardar la imagen
            ImageIO.write(barcodeImage, "PNG", fileToSave);
            JOptionPane.showMessageDialog(this, "Código de barras guardado exitosamente: " + fileToSave.getAbsolutePath());
        }
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al generar el código de barras:");
    }
    }//GEN-LAST:event_barrasCodeActionPerformed

    private void txt_destinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_destinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_destinoActionPerformed

    private void txt_origenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_origenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_origenActionPerformed

    private void activar_remisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activar_remisionActionPerformed
        activar_remision();
        limpiar_remision();
    }//GEN-LAST:event_activar_remisionActionPerformed

    private void bt_remisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_remisionActionPerformed
        Cls_Remision remision = new Cls_Remision();
        Cls_Pdfcreator pdfcreator = new Cls_Pdfcreator();
    // Crear un JFileChooser
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar PDF");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
    fileChooser.addChoosableFileFilter(filter);

    // Mostrar el diálogo de guardar archivo
    int userSelection = fileChooser.showSaveDialog(this);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        // Obtener la ruta del archivo seleccionado
        File fileToSave = fileChooser.getSelectedFile();
        String filePath = fileToSave.getAbsolutePath();
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            filePath += ".pdf"; // Asegurarse de que la extensión sea .pdf
        }

        // Llamar a crearPDF con la ruta seleccionada
        remision.crearPDF(txt_origen.getText(), txt_no_pro.getText(), txt_almacen.getText(), txt_pedido.getText(), txt_destino.getText(), txt_cliente.getText(), txt_linea.getText(), txt_autoriza.getText(), txt_transporte.getText(), txt_placas.getText(), txt_operador.getText(), txt_solicita.getText(), jtb_remision, filePath);
        //pdfcreator.exportarPDF(jtb_productos, filePath,txt_origen.getText(), txt_no_pro.getText(), txt_almacen.getText(), txt_pedido.getText(), txt_destino.getText(), txt_cliente.getText(), txt_linea.getText());
    }
    }//GEN-LAST:event_bt_remisionActionPerformed

    private void jtb_remisionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtb_remisionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtb_remisionMouseClicked

    private void txt_estadoActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }

    private void txt_cuerpotionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton activar_remision;
    private javax.swing.JButton barrasCode;
    private javax.swing.JButton bt_actualizar;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_guardar;
    private javax.swing.JButton bt_nuevo;
    private javax.swing.JButton bt_remision;
    private javax.swing.JComboBox<String> cbxCategoria;
    private javax.swing.JComboBox<String> cbxProveedor;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jtb_productos;
    private javax.swing.JTable jtb_remision;
    private javax.swing.JTextField txt_almacen;
    private javax.swing.JTextField txt_autoriza;
    private javax.swing.JTextField txt_cliente;
    private javax.swing.JTextField txt_codigo;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_destino;
    private javax.swing.JTextField txt_linea;
    private javax.swing.JTextField txt_no_pro;
    private javax.swing.JTextField txt_operador;
    private javax.swing.JTextField txt_origen;
    private javax.swing.JTextField txt_pedido;
    private javax.swing.JTextField txt_placas;
    private javax.swing.JTextField txt_solicita;
    private javax.swing.JTextField txt_transporte;
    private javax.swing.JTextField txt_ubicacion;
    // End of variables declaration//GEN-END:variables
}
