package vista;

import daos.DaoCategoria;
import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author barre
 */
public class Categoria extends javax.swing.JInternalFrame {

    public Categoria() {
        initComponents();
        this.setSize(new Dimension(400, 200));
        this.setTitle("Nueva Categoria");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_descripcion = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Añadir Categoría");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 20));

        txt_descripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyReleased(evt);
            }
        });
        getContentPane().add(txt_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 170, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, 30));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 170));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        modelos.Categoria categoria = new modelos.Categoria();
        DaoCategoria controlCategoria = new DaoCategoria();
        
        modelos.Categoria c;
        
        //validamos camoos vacios
        if (txt_descripcion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete  todos los campos");
        } else {
            c=controlCategoria.obtenerCategoria(txt_descripcion.getText());
            if (!controlCategoria.existeCategoria(txt_descripcion.getText().trim())) {
                categoria.setDescripcion(txt_descripcion.getText().trim());
                categoria.setEstado(1);
                if (controlCategoria.guardar(categoria)) {
                    JOptionPane.showMessageDialog(null, "Categoria Guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            } else if (c.getEstado()==0) {
                categoria.setDescripcion(txt_descripcion.getText().trim());
                categoria.setEstado(1);
                if (controlCategoria.guardar2(categoria)) {
                    JOptionPane.showMessageDialog(null, "Categoria Guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Guardar");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La Categoria ya esta registrada en la Base de Datos");
            }
        }
        //limpiar campo
        txt_descripcion.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_descripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == evt.VK_ENTER) {
            modelos.Categoria categoria = new modelos.Categoria();
            DaoCategoria controlCategoria = new DaoCategoria();

            //validamos camoos vacios
            if (txt_descripcion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Complete  todos los campos");
            } else {
                if (!controlCategoria.existeCategoria(txt_descripcion.getText().trim())) {
                    categoria.setDescripcion(txt_descripcion.getText().trim());
                    categoria.setEstado(1);
                    if (controlCategoria.guardar(categoria)) {
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al Guardar");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "La Categoria ya esta registrada en la Base de Datos");
                }
            }
            //limpiar campo
            txt_descripcion.setText("");
        }
    }//GEN-LAST:event_txt_descripcionKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    private javax.swing.JTextField txt_descripcion;
    // End of variables declaration//GEN-END:variables
}
