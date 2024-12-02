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

    public void ReporteMes(Date fecha) {
        Document documento = new Document();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
            String mesAnio = formato.format(fecha); // Extraer mes y año de la fecha
            String ruta = "src/reportesMes/reporte_" + mesAnio + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // Agregar encabezado e información del reporte
            Image header = Image.getInstance("src/img/logo.jpg");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas por Mes " + mesAnio + "\n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear tabla para mostrar los datos del reporte
            float[] columnsWidths = {3, 8, 5, 8, 4, 4};
            PdfPTable tablaCabecera = new PdfPTable(columnsWidths);
            tablaCabecera.addCell("Folio");
            tablaCabecera.addCell("Cliente");
            tablaCabecera.addCell("Empleado");
            tablaCabecera.addCell("Fecha");
            tablaCabecera.addCell("Total");
            tablaCabecera.addCell("Cantidad Productos");

            // Conectar a la base de datos y consultar la vista
            cn = Conexion.conectar();

            String sql = "SELECT folio, cliente, empleado, fecha, total, cantidadProductos "
                    + "FROM reporte_ventas_mes "
                    + "WHERE DATE_FORMAT(fecha, '%Y-%m') = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, mesAnio); // Filtrar por mes y año

            rs = pst.executeQuery();
            while (rs.next()) {
                tablaCabecera.addCell(rs.getString("folio"));
                tablaCabecera.addCell(rs.getString("cliente"));
                tablaCabecera.addCell(rs.getString("empleado"));
                tablaCabecera.addCell(rs.getString("fecha"));
                tablaCabecera.addCell(String.format("%.2f", rs.getDouble("total")));
                tablaCabecera.addCell(String.valueOf(rs.getInt("cantidadProductos")));
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

    public void ReporteEmpleado(Date fecha) {
        Document documento = new Document();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
            String mesAnio = formato.format(fecha); // Extraer mes y año de la fecha
            String ruta = "src/reportesEmpleados/reporte_" + mesAnio + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // Agregar encabezado e información del reporte
            Image header = Image.getInstance("src/img/logo.jpg");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas por Empleado - " + mesAnio + "\n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear tabla para mostrar los datos del reporte
            float[] columnsWidths = {6, 4, 4, 4};
            PdfPTable tablaCabecera = new PdfPTable(columnsWidths);
            tablaCabecera.addCell("Empleado");
            tablaCabecera.addCell("Total Ventas");
            tablaCabecera.addCell("Cantidad Ventas");
            tablaCabecera.addCell("Mes / Año");

            // Conectar a la base de datos y consultar la vista
            cn = Conexion.conectar();

            String sql = "SELECT empleado, totalVentas, cantidadVentas, anio, mes "
                    + "FROM reporte_ventas_empleado "
                    + "WHERE CONCAT(anio, '-', LPAD(mes, 2, '0')) = ?";
            pst = cn.prepareStatement(sql);
            pst.setString(1, mesAnio); // Filtrar por mes y año en formato YYYY-MM

            rs = pst.executeQuery();
            while (rs.next()) {
                tablaCabecera.addCell(rs.getString("empleado"));
                tablaCabecera.addCell("$" + String.format("%.2f", rs.getDouble("totalVentas")));
                tablaCabecera.addCell(String.valueOf(rs.getInt("cantidadVentas")));
                tablaCabecera.addCell(rs.getInt("mes") + " / " + rs.getInt("anio"));
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

    public void ReporteTrimestres(int anio) {
        Document documento = new Document();
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            String ruta = "src/reportesTrimestres/reporte_trimestres_" + anio + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // Agregar encabezado e información del reporte
            Image header = Image.getInstance("src/img/logo.jpg");
            header.scaleToFit(100, 100);
            header.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas por Trimestres - Año " + anio + "\n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear tabla para mostrar los datos del reporte
            float[] columnsWidths = {6, 4, 4, 4, 4};
            PdfPTable tablaCabecera = new PdfPTable(columnsWidths);
            tablaCabecera.addCell("Producto");
            tablaCabecera.addCell("Trimestre 1");
            tablaCabecera.addCell("Trimestre 2");
            tablaCabecera.addCell("Trimestre 3");
            tablaCabecera.addCell("Trimestre 4");

            // Conectar a la base de datos y consultar la vista
            cn = Conexion.conectar();

            String sql = "SELECT producto, trimestre_1, trimestre_2, trimestre_3, trimestre_4 "
                    + "FROM reporte_ventas_trimestres "
                    + "WHERE anio = ?";
            pst = cn.prepareStatement(sql);
            pst.setInt(1, anio); // Filtrar por año

            rs = pst.executeQuery();
            while (rs.next()) {
                tablaCabecera.addCell(rs.getString("producto"));
                tablaCabecera.addCell(String.valueOf(rs.getInt("trimestre_1")));
                tablaCabecera.addCell(String.valueOf(rs.getInt("trimestre_2")));
                tablaCabecera.addCell(String.valueOf(rs.getInt("trimestre_3")));
                tablaCabecera.addCell(String.valueOf(rs.getInt("trimestre_4")));
            }

            documento.add(tablaCabecera);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de trimestres: " + e);
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
