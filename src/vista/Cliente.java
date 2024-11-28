package vista;

import daos.DaoCliente;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author barre
 */
public class Cliente extends javax.swing.JInternalFrame {

    public Cliente(modelos.Cliente pro) {
        initComponents();
        this.setSize(new Dimension(400, 200));
        this.setTitle("Nuevo Cliente");

        btnEliminar.setVisible(false);
        btnActualizar.setVisible(false);
        if (pro != null) {
            txt_Nombre.setText("" + pro.getNombre());
            txt_Nombre.setEditable(false);
            txt_Telefono.setText(pro.getTelefono());
            btnActualizar.setVisible(true);
            jButton_Guardar.setVisible(false);
            btnEliminar.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar = new javax.swing.JButton();
        txt_Nombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txt_Telefono = new javax.swing.JTextField();
        jButton_Guardar = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 110, 30));
        getContentPane().add(txt_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 170, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Telefono:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nuevo Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 20));

        btnActualizar.setFont(new java.awt.Font("Segoe UI Variable", 0, 14)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/actualizar.png"))); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 60, 20));

        txt_Telefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 170, 30));

        jButton_Guardar.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jButton_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 120, 30));

        jLabel_wallpaper.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        jLabel_wallpaper.setText("Codigo");
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed

        if (txt_Nombre.getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Rellena los campos");
        } else {
            if (txt_Telefono.getText().equals(null)) {
                JOptionPane.showMessageDialog(null, "Rellena los campos");
            } else {
                DaoCliente cli = new DaoCliente();
                modelos.Cliente c = cli.buscarClientePorNombre(txt_Nombre.getText().trim());
                if (!cli.clienteExiste(txt_Nombre.getText())) {
                    cli.insertarCliente(txt_Nombre.getText().trim(), txt_Telefono.getText().trim());
                    this.Limpiar();
                    JOptionPane.showMessageDialog(null, "Se inserto el cliente");
                }else if(c.getEstado()==0){
                    cli.restaurarCliente(txt_Nombre.getText().trim());
                    JOptionPane.showMessageDialog(null, "Se inserto el cliente");
                }else{
                    JOptionPane.showMessageDialog(null, "El cliente ya existe");
                }
            }
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (txt_Nombre.getText().equals(null)) {
            JOptionPane.showMessageDialog(null, "Rellena los campos");
        } else {
            if (txt_Telefono.getText().equals(null)) {
                JOptionPane.showMessageDialog(null, "Rellena los campos");
            } else {
                DaoCliente cli = new DaoCliente();
                if (!cli.clienteExiste(txt_Nombre.getText())) {
                    cli.actualizarCliente(txt_Nombre.getText().trim(), txt_Telefono.getText().trim(), 1);
                    this.Limpiar();
                    JOptionPane.showMessageDialog(null, "Se inserto el cliente");
                }else{
                    JOptionPane.showMessageDialog(null, "El cliente ya existe");
                }
            }
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int option = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            DaoCliente cli = new DaoCliente();
            cli.eliminarCliente(txt_Nombre.getText().trim());
            this.setVisible(false);
            Facturacion f = new Facturacion(null);
            f.cargarClientes();
        } else {
            
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JTextField txt_Nombre;
    private javax.swing.JTextField txt_Telefono;
    // End of variables declaration//GEN-END:variables
     /**
     *
     * Metodo para limpiar campos
     */
    private void Limpiar() {
        txt_Telefono.setText("");
        txt_Nombre.setText("");
    }
}
