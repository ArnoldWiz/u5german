package vista;

import daos.DaoCategoria;
import daos.DaoCliente;
import daos.DaoProducto;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Categoria;
import static vista.Menu.jDesktopPane_menu;

/**
 *
 * @author barre
 */
public class GestionarProdcutos extends javax.swing.JInternalFrame {

    private int idCategoria;

    public GestionarProdcutos() {
        initComponents();
        this.setSize(new Dimension(1000, 400));
        this.setTitle("Gestionar Categorias");
        DaoProducto daoCategoria = new DaoProducto();
        DefaultTableModel model = daoCategoria.cargarTablaProductos();
        jTable_categorias.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton_nuevo = new javax.swing.JButton();
        jButton_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_categorias = new javax.swing.JTable();
        jButton_buscar = new javax.swing.JButton();
        jLabel_wallpaper1 = new javax.swing.JLabel();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jButton_nuevo.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nuevo.png"))); // NOI18N
        jButton_nuevo.setText("Agregar");
        jButton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_nuevoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 120, -1));

        jButton_editar.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jButton_editar.setText("Editar");
        jButton_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 120, -1));

        btn_eliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 120, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar Categorías");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Seleccionado:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descripcionActionPerformed(evt);
            }
        });
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, -1));

        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 190, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_categorias.setFont(new java.awt.Font("Segoe UI Variable", 0, 12)); // NOI18N
        jTable_categorias.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable_categorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_categoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_categorias);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 760, 250));

        jButton_buscar.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jButton_buscar.setText("Buscar");
        jButton_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_buscarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 120, -1));

        jLabel_wallpaper1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 370));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 790, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_buscarActionPerformed
        BuscarProduc interGestionarProducto = new BuscarProduc();
        jDesktopPane_menu.add(interGestionarProducto);
        interGestionarProducto.setVisible(true);
    }//GEN-LAST:event_jButton_buscarActionPerformed

    private void jTable_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_categoriasMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = jTable_categorias.rowAtPoint(evt.getPoint());
        if (filaSeleccionada > -1) { // Verificar que se seleccionó una fila válida
            // Suponiendo que el idCategoria está en la primera columna (índice 0)
            idCategoria = (int) jTable_categorias.getValueAt(filaSeleccionada, 0);
            String descripcion = (String) jTable_categorias.getValueAt(filaSeleccionada, 2);
            String codigo = String.valueOf(jTable_categorias.getValueAt(filaSeleccionada, 1));

            txt_nombre.setText(descripcion);
            txt_descripcion.setText(codigo);
        }
    }//GEN-LAST:event_jTable_categoriasMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (!txt_descripcion.getText().isEmpty()) {
            int option = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                DaoProducto cli = new DaoProducto();
                if (cli.desactivarProductoPorCodigo(Integer.parseInt(txt_descripcion.getText().trim()))) {
                    JOptionPane.showMessageDialog(null, "Se elimino correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar");
                }
                this.setVisible(false);
            } else {

            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void jButton_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editarActionPerformed
        // TODO add your handling code here:
        modelos.Producto producto = new modelos.Producto();
        DaoProducto daop = new DaoProducto();
        producto = daop.buscarProductoPorCodigo(txt_descripcion.getText().trim());
        if (producto != null) {
            if (producto.getEstado() == 1) {
                vista.Producto p = new vista.Producto(producto);
                jDesktopPane_menu.add(p);
                p.setVisible(true);
            }else
                JOptionPane.showMessageDialog(null, "Producto no encontrado");
        } else
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
    }//GEN-LAST:event_jButton_editarActionPerformed

    private void jButton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_nuevoActionPerformed
        // TODO add your handling code here:
        BuscarProduc interGestionarProducto = new BuscarProduc();
        jDesktopPane_menu.add(interGestionarProducto);
        interGestionarProducto.setVisible(true);
    }//GEN-LAST:event_jButton_nuevoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DaoProducto daoCategoria = new DaoProducto();
        DefaultTableModel model = daoCategoria.cargarTablaProductos();
        jTable_categorias.setModel(model);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_descripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descripcionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_buscar;
    private javax.swing.JButton jButton_editar;
    private javax.swing.JButton jButton_nuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JLabel jLabel_wallpaper1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_categorias;
    private javax.swing.JTextField txt_descripcion;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables

    private void EnviarDatosCategoriaSeleccionada(int idCategoria) {
        DaoCategoria daoCategoria = new DaoCategoria();
        Categoria categoria = daoCategoria.obtenerCategoriaPorId(idCategoria);
    }
}
