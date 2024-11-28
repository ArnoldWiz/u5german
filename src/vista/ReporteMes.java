package vista;

import com.toedter.calendar.JDateChooser;
import daos.DaoReportes;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author barre
 */
public class ReporteMes extends javax.swing.JInternalFrame {

    public static String fecha_inicio = "";

    public ReporteMes() {
        initComponents();
        this.setSize(new Dimension(450, 300));
        this.setTitle("Historial");
        jDateChooser_fecha_inicio.setDateFormatString("MM/yyyy"); // Configurar el formato

        // Configurar para mostrar solo mes y año
        SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
        jDateChooser_fecha_inicio.setDate(new Date()); // Fecha inicial
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
        jButton_Guardar = new javax.swing.JButton();
        jDateChooser_fecha_inicio = new com.toedter.calendar.JDateChooser();
        jLabel_wallpaper = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione mes y año");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fecha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 70, -1));

        jButton_Guardar.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jButton_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jButton_Guardar.setText("Buscar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 230, 50));

        jDateChooser_fecha_inicio.setDateFormatString("yyyy-MM-dd");
        jDateChooser_fecha_inicio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(jDateChooser_fecha_inicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 140, -1));

        jLabel_wallpaper.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo3.jpg"))); // NOI18N
        getContentPane().add(jLabel_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        java.util.Date fecha = jDateChooser_fecha_inicio.getDate();

        if (fecha != null) {
            java.sql.Date fechaInicio = new java.sql.Date(fecha.getTime());
            DaoReportes rep = new DaoReportes();
            rep.ReporteMes(fecha);
            JOptionPane.showMessageDialog(null, "Reporte de mes creado con éxito");
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione ambas fechas.");
        }
    }//GEN-LAST:event_jButton_GuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Guardar;
    private com.toedter.calendar.JDateChooser jDateChooser_fecha_inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_wallpaper;
    // End of variables declaration//GEN-END:variables

}
