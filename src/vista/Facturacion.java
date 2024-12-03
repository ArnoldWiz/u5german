package vista;

import conexion.Conexion;
import daos.DaoCliente;
import daos.DaoProducto;
import daos.DaoRegistrarVenta;
import daos.DaoVentaPDF;
import java.awt.Dimension;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import modelos.Venta;
import modelos.DetalleVenta;
import modelos.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author barre
 */
public class Facturacion extends javax.swing.JInternalFrame {

    private DefaultTableModel modeloDatosProductos;
    ArrayList<DetalleVenta> listaProductos = new ArrayList<>();
    private DetalleVenta producto;
    private int idCliente = 0;
    private int idProducto = 0;
    private String nombre = "";
    private int cantidadProductoBBDD = 0;
    private double precioUnitario = 0.0;
    private int porcentajeIva = 0;

    private int cantidad = 0;
    private double subtotal = 0.0;
    private double descuento = 0.0;
    private double iva = 0.0;
    private double totalPagar = 0.0;

    private double subtotalGeneral = 0.0;
    private double descuentoGeneral = 0.0;
    private double ivaGeneral = 0.0;
    private double totalPagarGeneral = 0.0;
    Usuario u;

    private int auxIdDetalle = 1;

    public Facturacion(Usuario us) {
        initComponents();
        this.setSize(new Dimension(800, 600));
        this.setTitle("Facturacion");
        this.inicializarTablaProducto();

        if (us == null) {
            us = new Usuario();
            us.setIdUsuario(1);
        }
        cargarClientes();
        u = us;
        txt_efectivo.setEnabled(false);
        jButton_calcular_cambio.setEnabled(false);
        txt_subtotal.setText("0.0");
        txt_iva.setText("0.0");
        txt_total_pagar.setText("0.0");
        ImageIcon wallpaper = new ImageIcon("src/img/fondo3.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(800, 600, WIDTH));
        jLabel_wallpaper.setIcon(icono);
        this.repaint();
    }

    //metodo para inicializar la tabla de los productos
    private void inicializarTablaProducto() {
        modeloDatosProductos = new DefaultTableModel();
        modeloDatosProductos.addColumn("N");
        modeloDatosProductos.addColumn("Nombre");
        modeloDatosProductos.addColumn("Cantidad");
        modeloDatosProductos.addColumn("P. Unitario");
        modeloDatosProductos.addColumn("SubTotal");
        modeloDatosProductos.addColumn("Descuento");
        modeloDatosProductos.addColumn("Iva");
        modeloDatosProductos.addColumn("Total Pagar");
        modeloDatosProductos.addColumn("Accion");
        this.jTable_productos.setModel(modeloDatosProductos);
    }

    private void listaTablaProductos() {
        this.modeloDatosProductos.setRowCount(listaProductos.size());
        for (int i = 0; i < listaProductos.size(); i++) {
            this.modeloDatosProductos.setValueAt(i + 1, i, 0);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getNombre(), i, 1);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getCantidad(), i, 2);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getPrecioUnitario(), i, 3);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getSubTotal(), i, 4);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getDescuento(), i, 5);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getIva(), i, 6);
            this.modeloDatosProductos.setValueAt(listaProductos.get(i).getTotalPagar(), i, 7);
            this.modeloDatosProductos.setValueAt("Eliminar", i, 8);
        }
        jTable_productos.setModel(modeloDatosProductos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jClientes = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_cant = new javax.swing.JTextField();
        txt_code = new javax.swing.JTextField();
        jButton_añadir_producto = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_productos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        txt_iva = new javax.swing.JTextField();
        txt_total_pagar = new javax.swing.JTextField();
        txt_efectivo = new javax.swing.JTextField();
        txt_cambio = new javax.swing.JTextField();
        jButton_calcular_cambio = new javax.swing.JButton();
        jButton_RegistrarVenta = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();
        jLabel_wallpaper1 = new javax.swing.JLabel();

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Producto:");

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 200, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 30, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Cliente:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 80, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("VENTA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Producto:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 80, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Cantidad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, 80, -1));

        txt_cant.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_cant.setText("1");
        getContentPane().add(txt_cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 100, 20));

        txt_code.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_codeActionPerformed(evt);
            }
        });
        getContentPane().add(txt_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, -1));

        jButton_añadir_producto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_añadir_producto.setText("Añadir");
        jButton_añadir_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_añadir_productoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_añadir_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 150, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_productos.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jTable_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_productosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_productos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 190));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 760, 210));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Subtotal:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Iva:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Total a pagar:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Efectivo:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Cambio:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        txt_subtotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_subtotal.setEnabled(false);
        jPanel2.add(txt_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 120, -1));

        txt_iva.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_iva.setEnabled(false);
        jPanel2.add(txt_iva, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 120, -1));

        txt_total_pagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_total_pagar.setEnabled(false);
        jPanel2.add(txt_total_pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 120, -1));

        txt_efectivo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel2.add(txt_efectivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 120, -1));

        txt_cambio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cambio.setEnabled(false);
        jPanel2.add(txt_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 120, -1));

        jButton_calcular_cambio.setBackground(new java.awt.Color(0, 204, 204));
        jButton_calcular_cambio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_calcular_cambio.setForeground(new java.awt.Color(255, 255, 255));
        jButton_calcular_cambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/billete.png"))); // NOI18N
        jButton_calcular_cambio.setText("Calcular Cambio");
        jButton_calcular_cambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_calcular_cambioActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_calcular_cambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 150, 170, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 420, 210));

        jButton_RegistrarVenta.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_RegistrarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check.png"))); // NOI18N
        jButton_RegistrarVenta.setText("Completar Venta");
        jButton_RegistrarVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton_RegistrarVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton_RegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarVentaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 170, 100));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, -10, -1, -1));

        jLabel_wallpaper1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -120, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_añadir_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_añadir_productoActionPerformed

        DaoProducto p = new DaoProducto();

        if (!txt_cant.getText().isEmpty()) {
            boolean validacion = validar(txt_cant.getText());
            if (validacion) {
                int cantidadIngresada = Integer.parseInt(txt_cant.getText());
                if (cantidadIngresada > 0) {
                    if (p.existeProductoCodigo(txt_code.getText().trim())) {
                        this.DatosDelProducto();

                        int stockDisponible = p.obtenerStockProducto(idProducto);
                        
                        if (cantidadIngresada <= stockDisponible) {
                            boolean productoExiste = false;
                            for (DetalleVenta dv : listaProductos) {
                                if (dv.getIdProducto() == idProducto) {
                                    if (dv.getCantidad() + cantidadIngresada <= stockDisponible) {
                                        dv.setCantidad(dv.getCantidad() + cantidadIngresada);
                                        dv.setSubTotal(dv.getPrecioUnitario() * dv.getCantidad());
                                        dv.setIva(dv.getSubTotal() * 0.16);
                                        dv.setDescuento(dv.getSubTotal() * 0.05);
                                        dv.setTotalPagar(dv.getSubTotal() + dv.getIva() - dv.getDescuento());

                                        dv.setSubTotal((double) Math.round(dv.getSubTotal() * 100) / 100);
                                        dv.setIva((double) Math.round(dv.getIva() * 100) / 100);
                                        dv.setDescuento((double) Math.round(dv.getDescuento() * 100) / 100);
                                        dv.setTotalPagar((double) Math.round(dv.getTotalPagar() * 100) / 100);

                                        productoExiste = true;
                                        break;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                                        return;
                                    }
                                }
                            }

                            if (!productoExiste) {
                                if (cantidadIngresada <= stockDisponible) {
                                    subtotal = precioUnitario * cantidadIngresada;
                                    totalPagar = subtotal + iva + descuento;

                                    producto = new DetalleVenta(auxIdDetalle, 1, idProducto, nombre, cantidadIngresada, precioUnitario, subtotal, descuento, iva, totalPagar, 1);
                                    listaProductos.add(producto);
                                    auxIdDetalle++;
                                }
                            }
                            this.CalcularTotalPagar();
                        } else {
                            JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró el producto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La cantidad no puede ser cero (0), ni negativa");
                }
            } else {
                JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numéricos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa la cantidad de productos");
        }
        this.listaTablaProductos();
    }//GEN-LAST:event_jButton_añadir_productoActionPerformed

    private void jButton_calcular_cambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_calcular_cambioActionPerformed
        if (!txt_efectivo.getText().isEmpty()) {
            boolean validacion = validarDouble(txt_efectivo.getText());
            if (validacion == true) {
                double efc = Double.parseDouble(txt_efectivo.getText().trim());
                double top = Double.parseDouble(txt_total_pagar.getText().trim());
                if (efc < top) {
                    JOptionPane.showMessageDialog(null, "El Dinero en efectivo no es suficiente");
                } else {
                    double cambio = (efc - top);
                    double cambi = (double) Math.round(cambio * 100d) / 100;
                    String camb = String.valueOf(cambi);
                    txt_cambio.setText(camb);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No de admiten caracteres no numericos");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese dinero en efectivo para calcular cambio");
        }
    }//GEN-LAST:event_jButton_calcular_cambioActionPerformed
    int idArrayList = 0;
    private void jTable_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_productosMouseClicked
        int fila_point = jTable_productos.rowAtPoint(evt.getPoint());
        int columna_point = 0;
        if (fila_point > -1) {
            int idArrayList = (int) modeloDatosProductos.getValueAt(fila_point, columna_point);

            if (SwingUtilities.isRightMouseButton(evt)) {
                int opcion = JOptionPane.showConfirmDialog(
                        null, "¿Eliminar Producto?",
                        "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION
                );

                switch (opcion) {
                    case JOptionPane.YES_OPTION:
                        listaProductos.remove(idArrayList - 1);
                        CalcularTotalPagar();
                        listaTablaProductos();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    default:
                        break;
                }
            }
            if (SwingUtilities.isLeftMouseButton(evt)) {
                jTable_productos.editCellAt(fila_point, columna_point);
            }
        }
    }//GEN-LAST:event_jTable_productosMouseClicked

    private void jButton_RegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarVentaActionPerformed

        Venta cabeceraVenta = new Venta();
        DaoRegistrarVenta controlVenta = new DaoRegistrarVenta();
        DaoCliente cliente = new DaoCliente();

        String fechaActual = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());

        if (listaProductos.size() > 0) {
            cabeceraVenta.setIdventa(0);
            cabeceraVenta.setIdUsuario(u.getIdUsuario());
            idCliente = cliente.ObtenerIdClientePorNombre((String) jClientes.getSelectedItem());
            cabeceraVenta.setIdCliente(idCliente);
            cabeceraVenta.setValorPagar(Double.parseDouble(txt_total_pagar.getText()));
            cabeceraVenta.setFechaVenta(fechaActual);
            cabeceraVenta.setEstado(1);

            List<DetalleVenta> detalles = new ArrayList<>();
            for (DetalleVenta elemento : listaProductos) {
                DetalleVenta detalleVenta = new DetalleVenta();
                detalleVenta.setIdDetalleVenta(0);
                detalleVenta.setIdVenta(0);
                detalleVenta.setIdProducto(elemento.getIdProducto());
                detalleVenta.setCantidad(elemento.getCantidad());
                detalleVenta.setPrecioUnitario(elemento.getPrecioUnitario());
                detalleVenta.setSubTotal(elemento.getSubTotal());
                detalleVenta.setDescuento(elemento.getDescuento());
                detalleVenta.setIva(elemento.getIva());
                detalleVenta.setTotalPagar(elemento.getTotalPagar());
                detalleVenta.setEstado(1);
                detalles.add(detalleVenta);
            }
            boolean resultado = controlVenta.guardarVentaCompleta(cabeceraVenta, detalles);
            if (resultado) {
                for (DetalleVenta detalle : detalles) {
                    RestarStockProductos(detalle.getIdProducto(), detalle.getCantidad());
                }

                JOptionPane.showMessageDialog(null, "¡Venta registrada exitosamente!");

                DaoVentaPDF pdf = new DaoVentaPDF();
                pdf.generarFacturaPDF();

                listaProductos.clear();
                listaTablaProductos();
                txt_subtotal.setText("0.0");
                txt_iva.setText("0.0");
                txt_total_pagar.setText("0.0");
                txt_efectivo.setText("");
                txt_cambio.setText("0.0");
                auxIdDetalle = 1;
            } else {
                JOptionPane.showMessageDialog(null, "¡Error al registrar la venta! Se deshicieron los cambios.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "¡Seleccione un producto!");
        }
    }//GEN-LAST:event_jButton_RegistrarVentaActionPerformed

    private void txt_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_codeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_codeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.cargarClientes();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_RegistrarVenta;
    private javax.swing.JButton jButton_añadir_producto;
    private javax.swing.JButton jButton_calcular_cambio;
    private javax.swing.JComboBox<String> jClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JLabel jLabel_wallpaper1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_productos;
    private javax.swing.JTextField txt_cambio;
    private javax.swing.JTextField txt_cant;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_efectivo;
    private javax.swing.JTextField txt_iva;
    private javax.swing.JTextField txt_subtotal;
    public static javax.swing.JTextField txt_total_pagar;
    // End of variables declaration//GEN-END:variables

    // metodo para cargar clientes
    public void cargarClientes() {
        DaoCliente c = new DaoCliente();
        jClientes = c.CargarComboClientes(jClientes);
    }

    // metodo para validar entero
    private boolean validar(String valor) {
        try {
            int num = Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // metodo para validar double
    private boolean validarDouble(String valor) {
        try {
            double num = Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // metodo para calcular iva
    private double CalcularIva(double precio) {
        iva = (precio * cantidad) * 0.16;
        return iva;
    }

    // metodo para calcular total
    private void CalcularTotalPagar() {
        subtotalGeneral = 0;
        descuentoGeneral = 0;
        ivaGeneral = 0;
        totalPagarGeneral = 0;

        for (DetalleVenta elemento : listaProductos) {
            subtotalGeneral += elemento.getSubTotal();
            ivaGeneral += elemento.getIva();
            totalPagarGeneral += elemento.getTotalPagar();
        }

        subtotalGeneral = (double) Math.round(subtotalGeneral * 100) / 100;
        ivaGeneral = (double) Math.round(ivaGeneral * 100) / 100;
        totalPagarGeneral = (double) Math.round(totalPagarGeneral * 100) / 100;

        txt_subtotal.setText(String.valueOf(subtotalGeneral));
        txt_iva.setText(String.valueOf(ivaGeneral));
        txt_total_pagar.setText(String.valueOf(totalPagarGeneral));
    }

    // metodo para restar stock
    private void RestarStockProductos(int idProducto, int cantidad) {
        Connection cn = null;
        int cantidadProductosBaseDeDatos = 0;
        try {
            cn = Conexion.conectar();
            cn.setAutoCommit(false);
            String sql = "SELECT cantidad, minimo FROM producto WHERE idProducto = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idProducto);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                cn.rollback();
                return;
            }

            cantidadProductosBaseDeDatos = rs.getInt("cantidad");
            int minimo = rs.getInt("minimo");

            if (cantidad > cantidadProductosBaseDeDatos) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock para restar.");
                cn.rollback();
                return;
            }

            String updateSql = "UPDATE producto SET cantidad = ? WHERE idProducto = ?";
            PreparedStatement updateStmt = cn.prepareStatement(updateSql);
            int cantidadNueva = cantidadProductosBaseDeDatos - cantidad;
            updateStmt.setInt(1, cantidadNueva);
            updateStmt.setInt(2, idProducto);

            if (updateStmt.executeUpdate() > 0) {
                cn.commit();
                JOptionPane.showMessageDialog(null, "Stock actualizado correctamente.");
                if (cantidadProductosBaseDeDatos - cantidad < minimo) {
                    JOptionPane.showMessageDialog(null, "El stock está por debajo del mínimo.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el stock.");
                cn.rollback();
            }
        } catch (SQLException e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex);
            }
            System.out.println("Error al restar cantidad de producto: " + e);
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }

    // metodo para obtener datos del producto
    private void DatosDelProducto() {
        Connection cn = null;
        try {
            cn = Conexion.conectar();
            cn.setAutoCommit(false);

            String sql = "select * from producto where codigo = " + txt_code.getText().trim();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                idProducto = rs.getInt("idProducto");
                nombre = rs.getString("nombre");
                cantidadProductoBBDD = rs.getInt("cantidad");
                precioUnitario = rs.getDouble("precio");
                porcentajeIva = rs.getInt("iva");
                this.CalcularIva(precioUnitario);
            }
            cn.commit();

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del producto: " + e);
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback: " + ex);
            }
        } finally {
            try {
                if (cn != null) {
                    cn.setAutoCommit(true);
                    cn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }
    }

}
