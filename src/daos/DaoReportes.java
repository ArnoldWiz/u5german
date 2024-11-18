package daos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import conexion.Conexion;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author barre
 */
public class DaoReportes {

    public void ReporteVentasConDetalles(Date fechaInicio, Date fechaFin) {
        Document documento = new Document();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String ruta = "src/reportes/reporte " + formato.format(fechaInicio) + " a " + formato.format(fechaFin) + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            Image header = Image.getInstance("src/img/logo.jpg");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas " + formato.format(fechaInicio) + " - " + formato.format(fechaFin) + "\n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            float[] columnsWidths = {3, 9, 4, 5};
            PdfPTable tablaCabecera = new PdfPTable(columnsWidths);
            tablaCabecera.addCell("Codigo");
            tablaCabecera.addCell("Tot. Pagar");
            tablaCabecera.addCell("Fecha Venta");
            tablaCabecera.addCell("Estado");
            
            cn = Conexion.conectar();
            
            String sql = "SELECT cv.idVenta, cv.valorPagar, cv.fechaVenta FROM venta cv WHERE cv.fechaVenta BETWEEN ? AND ?";
            pst = cn.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            pst.setDate(2, new java.sql.Date(fechaFin.getTime()));

            rs = pst.executeQuery();
            while (rs.next()) {
                String idVenta = rs.getString("idVenta");
                String valorPagar = String.format("%.2f", rs.getDouble("valorPagar")); 
                String fechaVenta = rs.getString("fechaVenta");

                tablaCabecera.addCell(idVenta);
                tablaCabecera.addCell(valorPagar);
                tablaCabecera.addCell(fechaVenta);
                tablaCabecera.addCell("Completado");
            }
            documento.add(tablaCabecera);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de ventas: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex);
            }
        }
        documento.close();
    }
}
