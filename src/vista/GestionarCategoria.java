package vista;

import daos.DaoCategoria;
import daos.DaoCliente;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelos.Categoria;

/**
 *
 * @author barre
 */
public class GestionarCategoria extends javax.swing.JInternalFrame {

    private int idCategoria;

    public GestionarCategoria() {
        initComponents();
        this.setSize(new Dimension(600, 400));
        this.setTitle("Gestionar Categorias");
        DaoCategoria daoCategoria = new DaoCategoria();
        DefaultTableModel model = daoCategoria.cargarTablaCategorias();
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

        btn_eliminar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_categorias = new javax.swing.JTable();
        jButton_actualizar = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_eliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 120, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar Categorías");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Descripción:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, 80));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 350, 250));

        jButton_actualizar.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        jButton_actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        jButton_actualizar.setText("Actualizar");
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, -1));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        if (!txt_descripcion.getText().isEmpty()) {
            Categoria categoria = new Categoria();
            DaoCategoria controlCategoria = new DaoCategoria();
            categoria.setDescripcion(txt_descripcion.getText().trim());
            if (controlCategoria.actualizar(categoria, idCategoria, 1)) {
                JOptionPane.showMessageDialog(null, "Categoria Actulizada");
                txt_descripcion.setText("");
                DaoCategoria daoCategoria = new DaoCategoria();
                DefaultTableModel model = daoCategoria.cargarTablaCategorias();
                jTable_categorias.setModel(model);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar Categoria");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria");
        }
    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jTable_categoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_categoriasMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = jTable_categorias.rowAtPoint(evt.getPoint());
        if (filaSeleccionada > -1) {
            idCategoria = (int) jTable_categorias.getValueAt(filaSeleccionada, 0);
            String descripcion = (String) jTable_categorias.getValueAt(filaSeleccionada, 1);
            txt_descripcion.setText(descripcion);
        }
    }//GEN-LAST:event_jTable_categoriasMouseClicked

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (!txt_descripcion.getText().isEmpty()) {
            int option = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                Categoria categoria = new Categoria();
                DaoCategoria controlCategoria = new DaoCategoria();
                categoria.setDescripcion(txt_descripcion.getText().trim());
                if (controlCategoria.actualizar(categoria, idCategoria, 0)) {
                    JOptionPane.showMessageDialog(null, "Categoria Eliminada");
                    txt_descripcion.setText("");
                    DaoCategoria daoCategoria = new DaoCategoria();
                    DefaultTableModel model = daoCategoria.cargarTablaCategorias();
                    jTable_categorias.setModel(model);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar Categoria");
                }
                this.setVisible(true);
            } else {

            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una categoria");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable_categorias;
    private javax.swing.JTextField txt_descripcion;
    // End of variables declaration//GEN-END:variables

<<<<<<< HEAD

=======
    private void EnviarDatosCategoriaSeleccionada(int idCategoria) {
        DaoCategoria daoCategoria = new DaoCategoria();
        Categoria categoria = daoCategoria.obtenerCategoriaPorId(idCategoria);
    }
>>>>>>> a33e09ef76daf7770e51bc5495a079d747fcb6eb
}
